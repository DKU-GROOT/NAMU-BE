package com.groot.namu.entity;

import com.groot.namu.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private String type;
    private String role;
    private String nickname;
    private int point;
    private boolean notice;

    public UserEntity (SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.point = 0;
        this.type = "app";
        this.role = "ROLE_USER";
        this.notice = true;
    }

    public UserEntity (String userId, String email, String type) {
        this.email = email;
        this.password = "P!ssw0rd";
        this.nickname = "관리자";
        this.point = 0;
        this.type = type;
        this.role = "ROLE_USER";
        this.notice = true;
    }

    public UserEntity (String email, String type) {
        this.email = email;
        this.password = "P!ssw0rd";
        this.nickname = "null";
        this.point = 0;
        this.type = "kakao";
        this.role = "ROLE_USER";
        this.notice = true;
    }

}
