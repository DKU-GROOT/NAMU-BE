package com.groot.namu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groot.namu.dto.request.study.StudyChatRequestDto;
import com.groot.namu.dto.response.study.StudyChatResponseDto;
import com.groot.namu.service.StudyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @PostMapping("/ask")
    public ResponseEntity<? super StudyChatResponseDto> studyChat (
        @RequestBody @Valid StudyChatRequestDto requestBody
    ) {
        ResponseEntity<? super StudyChatResponseDto> response = studyService.studyChat(requestBody);
        return response;
    }
}
