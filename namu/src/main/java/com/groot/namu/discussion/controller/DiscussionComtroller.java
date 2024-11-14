package com.groot.namu.discussion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groot.namu.discussion.service.DiscussionService;
import com.groot.namu.shop.dto.request.AddItemListRequestDto;
import com.groot.namu.discussion.dto.response.PostDiscussionResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/namu/v2/discussion")
public class DiscussionComtroller {

    private final DiscussionService discussionService; 

    @PostMapping("/post")
    public ResponseEntity<? super PostDiscussionResponseDto> post(
        @RequestBody @Valid AddItemListRequestDto requestBody
    ) {
        ResponseEntity<? super PostDiscussionResponseDto> response = discussionService.(requestBody);    
        return response;
    }
}
