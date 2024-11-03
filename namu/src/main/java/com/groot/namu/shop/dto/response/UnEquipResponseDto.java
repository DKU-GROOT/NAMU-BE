package com.groot.namu.shop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

public class UnEquipResponseDto extends ResponseDto{
    private UnEquipResponseDto() {
        super();
    }

    public static ResponseEntity<UnEquipResponseDto> success() {
        UnEquipResponseDto responseBody = new UnEquipResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
