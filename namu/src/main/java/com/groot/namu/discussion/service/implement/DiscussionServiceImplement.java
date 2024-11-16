package com.groot.namu.discussion.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.groot.namu.discussion.dto.request.PostDiscussionRequestDto;
import com.groot.namu.discussion.dto.response.PostDiscussionResponseDto;
import com.groot.namu.discussion.service.DiscussionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscussionServiceImplement implements DiscussionService{

    @Override
    public ResponseEntity<? super PostDiscussionResponseDto> post(PostDiscussionRequestDto dto) {
        
        return PostDiscussionResponseDto.success();
    }
    
}
