package com.groot.namu.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.common.ResponseCode;
import com.groot.namu.common.ResponseMessage;
import com.groot.namu.dto.response.ResponseDto;
import com.groot.namu.entity.UserEntity;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ResponseDto {

    private String token;
    private int expirationTime;
    private String nickname;
    private int point;
    private int treeLevel;
    private boolean notice;

    private SignInResponseDto (String token) {
        super();
        this.token = token;
        this.expirationTime = 3600;
    }

    private SignInResponseDto (String token, UserEntity user) {
        super();
        this.token = token;
        this.expirationTime = 3600;
        this.nickname = user.getNickname();
        this.point = user.getPoint();
        this.notice = user.isNotice();

    }

    public static ResponseEntity<SignInResponseDto> success(String token, UserEntity user) {
        SignInResponseDto responseBody = new SignInResponseDto(token, user);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail () {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}
