package com.groot.namu.user.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.user.common.UserResponseCode;
import com.groot.namu.user.common.UserResponseMessage;
import com.groot.namu.global.dto.ResponseDto;
import com.groot.namu.user.entity.UserEntity;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ResponseDto{
    private String token;
    private int expirationTime;
    private String nickname;
    private int point;
    private int treeLevel;
    private boolean notice;

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

    public static ResponseEntity<ResponseDto> signInFailEmail () {
        ResponseDto responseBody = new ResponseDto(UserResponseCode.EMAIL_MISSMATCH, UserResponseMessage.EMAIL_MISSMATCH);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFailPassword () {
        ResponseDto responseBody = new ResponseDto(UserResponseCode.PASSWORD_MISSMATCH, UserResponseMessage.PASSWORD_MISSMATCH);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
    
}
