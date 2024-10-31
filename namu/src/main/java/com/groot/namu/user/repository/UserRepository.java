package com.groot.namu.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
    boolean existsByEmail(String email);
    boolean eexistsByNickname(String nickname);

    UserEntity findByEmail(String email);
}
