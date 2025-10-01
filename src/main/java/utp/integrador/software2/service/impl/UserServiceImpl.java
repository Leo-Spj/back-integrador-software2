/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.service.impl;

import java.util.Collections;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utp.integrador.software2.config.JwtUtil;
import utp.integrador.software2.model.entity.User;
import utp.integrador.software2.model.entity.dto.LoginRequest;
import utp.integrador.software2.model.entity.dto.RegisterRequest;
import utp.integrador.software2.model.entity.dto.UserUpdate;
import utp.integrador.software2.repository.UserRepository;
import utp.integrador.software2.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(RegisterRequest request) {
        User user = User.builder()
                .fullName(request.fullName())
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .phone(request.phone())
                .isPremium(false)
                .build();
        return userRepository.save(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

    public Optional<User> getProfile(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateProfile(String email, UserUpdate update) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setFullName(update.fullName());
        user.setPhone(update.phone());
        return userRepository.save(user);
    }

    public User subscribePremium(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setPremium(true);
        return userRepository.save(user);
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        // Aquí conviertes tu entidad a un objeto que Spring Security pueda manejar
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())        
            .password(user.getPasswordHash()) 
            .authorities(Collections.emptyList())
            .build();
    }
}
