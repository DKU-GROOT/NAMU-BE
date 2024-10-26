package com.groot.namu.user.entity;

import org.antlr.v4.runtime.tree.Tree;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id
    private String email;

    private String nickname;

    private int point;

    private boolean notification;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Tree tree;

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
        this.point = 0;
        this.notification = true;
    }

}
