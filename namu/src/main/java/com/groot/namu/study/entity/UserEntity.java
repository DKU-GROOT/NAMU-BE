package com.groot.namu.study.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;


@Getter
@Entity(name="user")
@Table(name="user")
public class UserEntity {
    @Id
    private String email;
    private String nickname;

    public UserEntity () {}

    public UserEntity(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
