package com.groot.namu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groot.namu.dto.response.TreeResponseDto;
import com.groot.namu.service.TreeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tree")
@RequiredArgsConstructor
public class TreeContoller {

    private final TreeService treeService;

    @GetMapping("/{email}")
    public ResponseEntity<TreeResponseDto> getTreeById(@PathVariable String email) {
        TreeResponseDto treeDto = treeService.getTreeByEmail(email);
        if (treeDto != null) {
            return ResponseEntity.ok(treeDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
