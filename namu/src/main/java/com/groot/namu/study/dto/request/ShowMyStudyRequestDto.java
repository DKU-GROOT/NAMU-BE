package com.groot.namu.study.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ShowMyStudyRequestDto {
    @NotBlank
    private String email;
}
