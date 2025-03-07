package com.groot.namu.checkList.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompleteCheckListRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String date;
    @NotNull
    @Min(value = 1)
    private int num;
}
