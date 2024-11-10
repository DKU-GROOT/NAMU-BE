package com.groot.namu.study.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScoringRequestDto {
    @NotNull
    private int examId;

    private String userAnswer1;

    private String userAnswer2;

    private String userAnswer3;

    private String userAnswer4;

    private String userAnswer5;
}
