package com.example.SecurityService.repository;

import com.example.SecurityService.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredentials,Integer> {


    Optional<UserCredentials> findByName(String userName);
}
