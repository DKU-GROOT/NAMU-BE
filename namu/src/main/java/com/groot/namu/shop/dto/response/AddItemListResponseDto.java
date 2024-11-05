package com.groot.namu.shop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

import lombok.Getter;

@Getter
public class AddItemListResponseDto extends ResponseDto {
    private AddItemListResponseDto () {
        super();
    }

    public static ResponseEntity<AddItemListResponseDto> success() {
        AddItemListResponseDto responseBody = new AddItemListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
