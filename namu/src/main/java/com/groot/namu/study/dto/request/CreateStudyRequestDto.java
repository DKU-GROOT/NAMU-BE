package com.groot.namu.study.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateStudyRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String subjectName;
}
