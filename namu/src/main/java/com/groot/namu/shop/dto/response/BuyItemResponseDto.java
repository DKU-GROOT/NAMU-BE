package com.groot.namu.shop.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.common.ResponseCode;
import com.groot.namu.global.common.ResponseMessage;
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

    public static ResponseEntity<ResponseDto> duplicateId () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> certificationFail () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFRCATION_FAIL, ResponseMessage.CERTIFRCATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}