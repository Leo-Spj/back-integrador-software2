/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import utp.integrador.software2.model.entity.User;
import utp.integrador.software2.model.entity.dto.LoginRequest;
import utp.integrador.software2.model.entity.dto.RegisterRequest;
import utp.integrador.software2.model.entity.dto.UserUpdate;

public interface UserService {
    public User register(RegisterRequest request);
    public String login(LoginRequest request);
    public Optional<User> getProfile(String email);
    public User updateProfile(String email, UserUpdate update);
    public User subscribePremium(String email);
    public UserDetails loadUserByUsername(String email);
}
