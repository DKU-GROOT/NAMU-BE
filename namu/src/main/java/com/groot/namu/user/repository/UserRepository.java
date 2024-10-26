package com.groot.namu.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.user.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
}
