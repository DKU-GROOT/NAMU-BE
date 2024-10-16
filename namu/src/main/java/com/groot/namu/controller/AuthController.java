package com.groot.namu.controller;

import org.springframework.web.bind.annotation.RestController;

import com.groot.namu.dto.request.auth.CheckCertificationRequestDto;
import com.groot.namu.dto.request.auth.EmailCertificationRequestDto;
import com.groot.namu.dto.request.auth.EmailCheckRequestDto;
import com.groot.namu.dto.request.auth.NicknameCheckRequestDto;
import com.groot.namu.dto.request.auth.SignInRequestDto;
import com.groot.namu.dto.request.auth.SignUpRequestDto;
import com.groot.namu.dto.response.auth.CheckCertificationResponseDto;
import com.groot.namu.dto.response.auth.EmailCertificationResponseDto;
import com.groot.namu.dto.response.auth.EmailCheckResponseDto;
import com.groot.namu.dto.response.auth.NicknameCheckResponseDto;
import com.groot.namu.dto.response.auth.SignInResponseDto;
import com.groot.namu.dto.response.auth.SignUpResponseDto;
import com.groot.namu.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/email-check")
    public ResponseEntity<? super EmailCheckResponseDto> emailCheck (
        @RequestBody @Valid EmailCheckRequestDto requestBody
    ) {
        ResponseEntity<? super EmailCheckResponseDto> response = authService.emailCheck(requestBody);
        return response;
    }
    
    @PostMapping("/email-certification")
    public  ResponseEntity<? super EmailCertificationResponseDto> emailCertification (
        @RequestBody @Valid EmailCertificationRequestDto requestBody
        ) {
            ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
            return response;
        }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification (
        @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

    @PostMapping("/nickname-check")
    public ResponseEntity<? super NicknameCheckResponseDto> nicknameCheck (
        @RequestBody @Valid NicknameCheckRequestDto requestBody
    ) {
        ResponseEntity<? super NicknameCheckResponseDto> response = authService.nicknameCheck(requestBody);
        return response;
    }
    
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
    
    
    
}
