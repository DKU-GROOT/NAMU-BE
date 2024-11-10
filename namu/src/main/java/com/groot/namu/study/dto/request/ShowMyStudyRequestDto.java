package com.groot.namu.study.dto.request;

import com.groot.namu.study.common.StudyResponseCode;
import com.groot.namu.study.common.StudyResponseMessage;
import com.groot.namu.study.dto.response.ResponseDto;
import com.groot.namu.study.dto.response.ShowMyStudyResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ShowMyStudyRequestDto {
    @NotBlank
    private String email;
}
