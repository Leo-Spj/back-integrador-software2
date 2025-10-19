/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.integrador.software2.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import utp.integrador.software2.config.JwtUtil;
import utp.integrador.software2.model.entity.User;
import utp.integrador.software2.model.entity.dto.AuthResponse;
import utp.integrador.software2.model.entity.dto.LoginRequest;
import utp.integrador.software2.model.entity.dto.RegisterRequest;
import utp.integrador.software2.model.entity.dto.UserResponse;
import utp.integrador.software2.model.entity.dto.UserUpdate;
import utp.integrador.software2.model.entity.mapper.UserMapper;
import utp.integrador.software2.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public UserController(UserService userService, JwtUtil jwtUtil, UserMapper userMapper) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @PostMapping("/auth/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return userMapper.toUserResponse(user);
    }
    
    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = userService.login(request);
        return new AuthResponse(token);
    }

    @GetMapping("/user/profile")
    public UserResponse profile(Authentication auth) {
        User user = userService.getProfile(auth.getName()).orElseThrow();
        return userMapper.toUserResponse(user);
    }

    @PutMapping("/user/profile")
    public UserUpdate updateProfile(Authentication auth, @RequestBody UserUpdate update) {
        User user = userService.updateProfile(auth.getName(), update);
        return userMapper.toUserUpdate(user);
    }

    @PostMapping("/user/subscribe-premium")
    public UserResponse subscribePremium(Authentication auth) {
        User user = userService.subscribePremium(auth.getName());
        return userMapper.toUserResponse(user);
    }
}
