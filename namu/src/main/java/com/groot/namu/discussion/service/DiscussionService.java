package com.groot.namu.discussion.service;

import org.springframework.http.ResponseEntity;

import com.groot.namu.discussion.dto.request.PostDiscussionRequestDto;
import com.groot.namu.discussion.dto.response.PostDiscussionResponseDto;

public interface DiscussionService {
        ResponseEntity<? super PostDiscussionResponseDto> post(PostDiscussionRequestDto dto);

}