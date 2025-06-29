package com.pm.authservice.service;

import com.pm.authservice.model.Users;
import com.pm.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<Users> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
