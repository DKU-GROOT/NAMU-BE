package com.groot.namu.tree.dto.response;

import com.groot.namu.tree.entity.TreeEntity;

import lombok.Getter;

@Getter
public class TreeResponseDto {
    private String email;
    private String treeDecoBottom;
    private String treeDecoTop;
    private int treeLevel;
    private int treePoint;

    public TreeResponseDto(TreeEntity entity) {
        this.email = entity.getEmail();
        this.treeDecoBottom = entity.getTreeDecoBottom();
        this.treeDecoTop = entity.getTreeDecoTop();
        this.treeLevel = entity.getTreeLevel();
        this.treePoint = entity.getTreePoint();
    }
}
