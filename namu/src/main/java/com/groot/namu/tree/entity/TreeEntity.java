package com.groot.namu.tree.entity;

import jakarta.persistence.Id;
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
