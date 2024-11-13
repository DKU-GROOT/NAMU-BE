package com.groot.namu.study.controller;

import com.groot.namu.study.dto.request.*;
import com.groot.namu.study.dto.response.*;
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

    @PostMapping("/scoring")
    public ResponseEntity<? super ScoringResponseDto> scoring(
            @RequestBody @Valid ScoringRequestDto requestBody
    ){
        ResponseEntity<? super ScoringResponseDto> scoringResponse = studyService.Scoring(requestBody);
        return scoringResponse;
    }

//    @PostMapping("/myStudy")
//    public ResponseEntity<? super ShowMyStudyResponseDto> showMyStudy(
//            @RequestBody @Valid ShowMyStudyRequestDto requestBody
//    ){
//        ResponseEntity<? super ShowMyStudyResponseDto> showMyStudyResponse = studyService.showMyStudy(requestBody);
//        return showMyStudyResponse;
//    }
}