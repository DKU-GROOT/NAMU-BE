package com.groot.namu.study.controller;

import com.groot.namu.study.dto.request.ChatRequestDto;
import com.groot.namu.study.dto.request.ExamRequestDto;
import com.groot.namu.study.dto.request.SummaryRequestDto;
import com.groot.namu.study.dto.response.ChatResponseDto;
import com.groot.namu.study.dto.response.ExamResponseDto;
import com.groot.namu.study.dto.response.SummaryResponseDto;
import com.groot.namu.study.service.StudyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("namu/v2/study")
@RequiredArgsConstructor
public class StudyController {
    private final StudyService studyService;

    @PostMapping("/ask")
    public ResponseEntity<? super ChatResponseDto> chat(
            @RequestBody @Valid ChatRequestDto requestBody
    ) {
        ResponseEntity<? super ChatResponseDto> chatResponse = studyService.chat(requestBody);
        return chatResponse;
    }

    @PostMapping("/summary")
    public ResponseEntity<? super SummaryResponseDto> summary(
            @RequestBody @Valid SummaryRequestDto requestBody
    ){
        ResponseEntity<? super SummaryResponseDto> summaryResponse = studyService.summary(requestBody);
        return summaryResponse;
    }

    @PostMapping("/exam")
    public ResponseEntity<? super ExamResponseDto> exam(
            @RequestBody @Valid ExamRequestDto requestBody
    ){
        ResponseEntity<? super ExamResponseDto> examResponse = studyService.exam(requestBody);
        return examResponse;
    }
}