package com.groot.namu.study.dto.request;

import com.groot.namu.study.common.ResponseCode;
import com.groot.namu.study.common.ResponseMessage;
import com.groot.namu.study.dto.response.ChatResponseDto;
import com.groot.namu.study.dto.response.ResponseDto;
import com.groot.namu.study.dto.response.SummaryResponseDto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
public class SummaryRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String subjectName;
}