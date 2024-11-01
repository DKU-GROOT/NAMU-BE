package com.groot.namu.user.entity;

import com.groot.namu.user.dto.request.SignUpRequestDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class UserEntity {
     @Id
    private String email;
    private String password;
    private String nickname;
    private String role;
    private int point;
    private boolean notice;

    public UserEntity (SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.role = "ROLE_USER";
        this.point = 0;
        this.notice = true;
    }
}
