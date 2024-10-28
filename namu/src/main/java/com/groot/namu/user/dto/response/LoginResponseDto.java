package com.groot.namu.user.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.common.ResponseCode;
import com.groot.namu.global.common.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private String code;
    private String message;
    private String email;

    public LoginResponseDto() {
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
        this.email = null;
    }

    public LoginResponseDto(String c, String m){
        this.code = c;
        this.message = m;
        this.email = null;
    }

    public LoginResponseDto(String email) {
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
        this.email = email;
    }

    public static ResponseEntity<LoginResponseDto> databaseError() {
        LoginResponseDto responseBody = new LoginResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<LoginResponseDto> validationFail() {
        LoginResponseDto responseBody = new LoginResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<LoginResponseDto> userNotExists() {
        LoginResponseDto responseBody = new LoginResponseDto(ResponseCode.USER_NOT_EXISTS, ResponseMessage.USER_NOT_EXISTS);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    
}
