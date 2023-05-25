package com.example.SecurityService.service;

import com.example.SecurityService.entity.UserCredentials;
import com.example.SecurityService.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserCredentialRepository userCredentialRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    public String saveUser(UserCredentials userCredentials) {
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        userCredentialRepository.save(userCredentials);
        return "User Added to the system";

    }


    public String GenerateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
