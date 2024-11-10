package com.groot.namu.study.service;

import com.groot.namu.study.dto.request.*;
import com.groot.namu.study.dto.response.*;
import org.springframework.http.ResponseEntity;

public interface StudyService {
    ResponseEntity<? super ChatResponseDto> chat(ChatRequestDto dto);

    ResponseEntity<? super SummaryResponseDto> summary(SummaryRequestDto dto);

    ResponseEntity<? super ExamResponseDto> exam(ExamRequestDto dto);

    ResponseEntity<? super ScoringResponseDto> Scoring(ScoringRequestDto dto);

    ResponseEntity<? super ShowMyStudyResponseDto> showMyStudy(ShowMyStudyRequestDto dto);
}
