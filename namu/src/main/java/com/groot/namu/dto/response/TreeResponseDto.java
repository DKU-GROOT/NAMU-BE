package com.groot.namu.dto.response;

import com.groot.namu.entity.TreeEntity;

import lombok.Getter;

@Getter
public class TreeResponseDto extends ResponseDto{
    private String email;
    private String treeDecoBottom;
    private String treeDecoTop;
    private int treeLevel;
    private int treePoint;

    // TreeEntity를 받아서 DTO로 변환하는 생성자
    public TreeResponseDto(TreeEntity entity) {
        this.email = entity.getEmail();
        this.treeDecoBottom = entity.getTreeDecoBottom();
        this.treeDecoTop = entity.getTreeDecoTop();
        this.treeLevel = entity.getTreeLevel();
        this.treePoint = entity.getTreePoint();
    }

}
