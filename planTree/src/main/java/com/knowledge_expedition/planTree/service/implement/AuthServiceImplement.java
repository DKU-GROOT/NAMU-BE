package com.knowledge_expedition.planTree.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.knowledge_expedition.planTree.dto.request.auth.IdCheckRequestDto;
import com.knowledge_expedition.planTree.dto.response.ResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.IdCheckResponseDto;
import com.knowledge_expedition.planTree.repository.UserRepository;
import com.knowledge_expedition.planTree.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        
        try {

            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return IdCheckResponseDto.duplicatedId();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }
    
}
