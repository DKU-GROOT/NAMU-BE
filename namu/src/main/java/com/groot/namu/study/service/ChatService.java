package com.groot.namu.study.service;

import com.groot.namu.study.dto.request.ChatRequestDto;
import com.groot.namu.study.dto.request.ExamRequestDto;
import com.groot.namu.study.dto.request.SummaryRequestDto;
import com.groot.namu.study.dto.response.ChatResponseDto;
import com.groot.namu.study.dto.response.ExamResponseDto;
import com.groot.namu.study.dto.response.SummaryResponseDto;
import org.springframework.http.ResponseEntity;

public interface ChatService {
    ResponseEntity<? super ChatResponseDto> chat(ChatRequestDto dto);

    ResponseEntity<? super SummaryResponseDto> summary(SummaryRequestDto dto);

    ResponseEntity<? super ExamResponseDto> exam(ExamRequestDto dto);
}
