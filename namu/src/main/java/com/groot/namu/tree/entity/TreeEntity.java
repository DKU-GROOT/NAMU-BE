package com.groot.namu.tree.entity;

import com.groot.namu.user.entity.*;
import jakarta.persistence.*;
import lombok.Getter;

@Entity(name="tree")
@Getter
@Table(name="tree")
public class TreeEntity {
    @Id
    private String email;
    private int treeLevel;
    private int treePoint;
    private String treeDecoTop;
    private String treeDecoBottom;

    public TreeEntity(String email) {
        this.email = email;
        this.treeLevel = 1;
        this.treePoint = 10;
        this.treeDecoTop = null;
        this.treeDecoBottom = null;
    }
}
