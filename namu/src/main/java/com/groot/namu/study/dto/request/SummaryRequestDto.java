package com.groot.namu.study.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SummaryRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String subjectName;
}