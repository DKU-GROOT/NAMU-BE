package com.groot.namu.user.service;

import org.springframework.http.ResponseEntity;

import com.groot.namu.user.dto.request.CheckCertificationRequestDto;
import com.groot.namu.user.dto.request.EmailCertificationRequestDto;
import com.groot.namu.user.dto.request.EmailCheckRequestDto;
import com.groot.namu.user.dto.request.NicknameCheckRequestDto;
import com.groot.namu.user.dto.request.SignInRequestDto;
import com.groot.namu.user.dto.request.SignUpRequestDto;
import com.groot.namu.user.dto.response.CheckCertificationResponseDto;
import com.groot.namu.user.dto.response.SignInResponseDto;
import com.groot.namu.user.dto.response.NicknameCheckResponseDto;
import com.groot.namu.user.dto.response.EmailCheckResponseDto;
import com.groot.namu.user.dto.response.EmailCertificationResponseDto;
import com.groot.namu.user.dto.response.SignUpResponseDto;

public interface UserService {
    ResponseEntity<? super EmailCheckResponseDto> emailCheck(EmailCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp (SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto);
    ResponseEntity<? super NicknameCheckResponseDto> nicknameCheck(NicknameCheckRequestDto dto);
}
