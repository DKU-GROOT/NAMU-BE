package com.knowledge_expedition.planTree.service;

import org.springframework.http.ResponseEntity;

import com.knowledge_expedition.planTree.dto.request.auth.CheckCertificationRequestDto;
import com.knowledge_expedition.planTree.dto.request.auth.EmailCertificationRequestDto;
import com.knowledge_expedition.planTree.dto.request.auth.IdCheckRequestDto;
import com.knowledge_expedition.planTree.dto.request.auth.SignUpRequestDto;
import com.knowledge_expedition.planTree.dto.response.auth.CheckCertificationResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.EmailCertificationResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.IdCheckResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.SignUpResponseDto;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp (SignUpRequestDto dto);
}
