package com.groot.namu.service;

import org.springframework.http.ResponseEntity;

import com.groot.namu.dto.request.study.StudyChatRequestDto;
import com.groot.namu.dto.response.study.StudyChatResponseDto;;

public interface StudyService {
        ResponseEntity<? super StudyChatResponseDto> studyChat(StudyChatRequestDto dto);
}
