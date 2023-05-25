package com.example.SecurityService.config;

import com.example.SecurityService.entity.UserCredentials;
import com.example.SecurityService.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class    CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> userCredentials=userCredentialRepository.findByName(username);
      return   userCredentials.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User Not found"));


    }
}
