package com.groot.namu.tree.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groot.namu.tree.dto.response.TreeResponseDto;
import com.groot.namu.tree.service.TreeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("namu/v2/tree")
@RequiredArgsConstructor
public class TreeController {
    
    private final TreeService treeService;

    @GetMapping("/{email}")
    public ResponseEntity<TreeResponseDto> getTreeById(@PathVariable String email) {
        TreeResponseDto responseBody = treeService.getTreeByEmail(email);
        if (responseBody != null) {
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
