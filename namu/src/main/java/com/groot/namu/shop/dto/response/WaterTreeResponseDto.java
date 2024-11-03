package com.groot.namu.shop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

import lombok.Getter;

@Getter
public class WaterTreeResponseDto extends ResponseDto {
    private WaterTreeResponseDto() {
        super();
    }

    public static ResponseEntity<WaterTreeResponseDto> success() {
        WaterTreeResponseDto responseBody = new WaterTreeResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
