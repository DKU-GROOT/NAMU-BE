package com.groot.namu.checkList.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckCheckListRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String date;
}
