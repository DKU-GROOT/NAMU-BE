package com.groot.namu.shop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

import lombok.Getter;

@Getter
public class BuyItemResponseDto extends ResponseDto{
    private BuyItemResponseDto() {
        super();
    }

    public static ResponseEntity<BuyItemResponseDto> success() {
        BuyItemResponseDto responseBody = new BuyItemResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}