package com.groot.namu.discussion.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDiscussionRequestDto {
    
    @NotBlank
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotBlank
    private String subjectName;

    private boolean anonymous;
}
