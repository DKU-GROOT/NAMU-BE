package com.groot.namu.shop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.common.ResponseCode;
import com.groot.namu.global.common.ResponseMessage;
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

    public static ResponseEntity<ResponseDto> certificationFail () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFRCATION_FAIL, ResponseMessage.CERTIFRCATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
