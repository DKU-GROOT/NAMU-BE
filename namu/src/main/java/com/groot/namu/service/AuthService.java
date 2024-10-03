package com.groot.namu.service;

import org.springframework.http.ResponseEntity;

import com.groot.namu.dto.request.auth.CheckCertificationRequestDto;
import com.groot.namu.dto.request.auth.EmailCertificationRequestDto;
import com.groot.namu.dto.request.auth.IdCheckRequestDto;
import com.groot.namu.dto.request.auth.SignInRequestDto;
import com.groot.namu.dto.request.auth.SignUpRequestDto;
import com.groot.namu.dto.response.auth.CheckCertificationResponseDto;
import com.groot.namu.dto.response.auth.EmailCertificationResponseDto;
import com.groot.namu.dto.response.auth.IdCheckResponseDto;
import com.groot.namu.dto.response.auth.SignInResponseDto;
import com.groot.namu.dto.response.auth.SignUpResponseDto;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp (SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto);
}
