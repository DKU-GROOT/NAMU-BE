package com.knowledge_expedition.planTree.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.knowledge_expedition.planTree.dto.request.auth.CheckCertificationRequestDto;
import com.knowledge_expedition.planTree.dto.request.auth.EmailCertificationRequestDto;
import com.knowledge_expedition.planTree.dto.request.auth.IdCheckRequestDto;
import com.knowledge_expedition.planTree.dto.response.ResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.CheckCertificationResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.EmailCertificationResponseDto;
import com.knowledge_expedition.planTree.dto.response.auth.IdCheckResponseDto;
import com.knowledge_expedition.planTree.entity.CertificationEntity;
import com.knowledge_expedition.planTree.provider.EmailProvider;
import com.knowledge_expedition.planTree.repository.CertificationRepository;
import com.knowledge_expedition.planTree.repository.UserRepository;
import com.knowledge_expedition.planTree.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;

    private final EmailProvider emailProvider;
    
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

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        
        try {

            String userId = dto.getId();
            String email = dto.getEmail();

            //존재하는 이메일인지 확인
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return EmailCertificationResponseDto.duplicateId();

            //인증번호 생성
            String certificationNumber = getCertificationNumber();

            //메일 전송
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            //메일 전송 결과 저장
            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
            certificationRepository.save(certificationEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    private static String getCertificationNumber() {

        String certificationNumber = "";

        for(int count = 0; count < 4; count++) certificationNumber += (int) (Math.random() * 10);

        return certificationNumber;
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {

            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            if (certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched) return CheckCertificationResponseDto.certificationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }
    
}
