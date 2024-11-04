package com.groot.namu.tree.service;

import org.springframework.stereotype.Service;

import com.groot.namu.tree.dto.response.TreeResponseDto;
import com.groot.namu.tree.entity.TreeEntity;
import com.groot.namu.tree.repository.TreeRepository;

@Service
public class TreeService {
    private final TreeRepository treeRepository;

    public TreeService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    
    public TreeResponseDto getTreeByEmail(String email) {
        TreeEntity treeEntity = treeRepository.findByEmail(email);
        return (treeEntity != null) ? new TreeResponseDto(treeEntity) : null;
    }
}
