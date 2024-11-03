package com.groot.namu.shop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

public class UnEquipItemResponseDto extends ResponseDto{
    private UnEquipItemResponseDto() {
        super();
    }

    public static ResponseEntity<UnEquipItemResponseDto> success() {
        UnEquipItemResponseDto responseBody = new UnEquipItemResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
