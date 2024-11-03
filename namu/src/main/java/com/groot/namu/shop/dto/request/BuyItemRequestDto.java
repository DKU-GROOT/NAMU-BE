package com.groot.namu.shop.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BuyItemRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String itemName;
}
