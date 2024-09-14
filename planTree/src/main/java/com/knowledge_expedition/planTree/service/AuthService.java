package com.knowledge_expedition.planTree.service;

import org.springframework.http.ResponseEntity;

import com.knowledge_expedition.planTree.dto.request.auth.IdCheckRequestDto;
import com.knowledge_expedition.planTree.dto.response.auth.IdCheckResponseDto;

import jakarta.validation.Valid;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(@Valid IdCheckRequestDto requestBody);
    
}
