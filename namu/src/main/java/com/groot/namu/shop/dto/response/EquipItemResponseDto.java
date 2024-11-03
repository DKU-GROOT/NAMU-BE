package com.groot.namu.shop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

import lombok.Getter;

@Getter
public class EquipItemResponseDto extends ResponseDto{
    private EquipItemResponseDto() {
        super();
    }

    public static ResponseEntity<EquipItemResponseDto> success() {
        EquipItemResponseDto responseBody = new EquipItemResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
