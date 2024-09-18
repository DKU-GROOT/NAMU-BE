package com.knowledge_expedition.planTree.controller;

import org.springframework.web.bind.annotation.RestController;

import com.knowledge_expedition.planTree.dto.request.auth.CheckCertificationRequestDto;
import com.knowledge_expedition.planTree.dto.request.auth.EmailCertificationRequestDto;
import com.knowledge_expedition.planTree.dto.request.auth.IdCheckRequestDto;
import com.knowledge_expedition.planTree.dto.response.auth.CheckCertificationResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.EmailCertificationResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.IdCheckResponseDto;
import com.knowledge_expedition.planTree.service.AuthService;

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

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (
        @RequestBody @Valid IdCheckRequestDto requestBody
    ) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
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
    
    
    
}
