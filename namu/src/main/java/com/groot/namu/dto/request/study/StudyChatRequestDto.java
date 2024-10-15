package com.groot.namu.dto.request.study;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudyChatRequestDto {
    
    @NotBlank
    private String email;
    @NotBlank
    private String subjectName;
    @NotBlank
    private String question;
    
}
