package com.groot.namu.shop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddItemListRequestDto {
    @NotBlank
    private String itemName;
    @NotBlank
    private int price;
    @NotBlank
    private String type;
}
