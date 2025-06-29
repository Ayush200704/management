package com.pm.authservice.service;

import com.pm.authservice.dtos.LoginRequestDTO;
import com.pm.authservice.security.jwt.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO){
        Optional<String> token = userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getPassword()));

        return token;
    }

    public boolean validateToken(String token){
        try{
            jwtUtil.validateToken(token);
            return true;
        }catch(JwtException e){
            return false;
        }
    }

}
