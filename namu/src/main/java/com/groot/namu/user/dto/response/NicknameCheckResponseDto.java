package com.groot.namu.user.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.user.common.UserResponseCode;
import com.groot.namu.user.common.UserResponseMessage;
import com.groot.namu.global.dto.ResponseDto;

import lombok.Getter;

@Getter
public class NicknameCheckResponseDto extends ResponseDto{
    private NicknameCheckResponseDto() {
        super();
    }

    public static ResponseEntity<NicknameCheckResponseDto> success() {
        NicknameCheckResponseDto responseBody = new NicknameCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedId() {
        ResponseDto responseBody = new ResponseDto(UserResponseCode.DUPLICATE_ID, UserResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
