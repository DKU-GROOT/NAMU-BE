package com.groot.namu.user.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.common.ResponseCode;
import com.groot.namu.global.common.ResponseMessage;
import com.groot.namu.global.dto.ResponseDto;

import lombok.Getter;

@Getter
public class NicknameCheckResponseDto {
    private NicknameCheckResponseDto() {
        super();
    }

    public static ResponseEntity<NicknameCheckResponseDto> success() {
        NicknameCheckResponseDto responseBody = new NicknameCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
