package com.groot.namu.discussion.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

public class PostDiscussionResponseDto extends ResponseDto{
    private PostDiscussionResponseDto() {
        super();
    }

    public static ResponseEntity<PostDiscussionResponseDto> success() {
        PostDiscussionResponseDto responseBody = new PostDiscussionResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
