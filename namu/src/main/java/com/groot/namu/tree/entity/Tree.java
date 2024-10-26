package com.groot.namu.tree.entity;

import com.groot.namu.user.entity.*;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Tree {
    public Tree(User user) {
        this.email = user.getEmail();
        this.treeLevel = 1;
        this.treePoint = 10;
        this.treeDecoTop = null;
        this.treeDecoBottom = null;    
    }

    @Id
    private String email;

    private int treeLevel;
    private int treePoint;
    private String treeDecoTop;
    private String treeDecoBottom;

    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;
}
