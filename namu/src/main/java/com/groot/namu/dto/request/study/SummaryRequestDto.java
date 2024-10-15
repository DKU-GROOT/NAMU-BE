package com.groot.namu.dto.request.study;

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
