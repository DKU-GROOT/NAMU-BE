package com.groot.namu.user.entity;

import com.groot.namu.user.dto.request.SignUpRequestDto;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name="user")
@Getter
@Table(name="user")
public class UserEntity {
     @Id
    private String email;
    private String password;
    private String nickname;
    private int point;
    private boolean notice;

    public UserEntity (SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.point = 0;
        this.notice = true;
    }
}
