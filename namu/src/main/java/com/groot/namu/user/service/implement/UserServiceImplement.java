package com.groot.namu.user.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.groot.namu.global.dto.ResponseDto;
import com.groot.namu.global.provider.EmailProvider;
import com.groot.namu.global.provider.JwtProvider;
import com.groot.namu.tree.entity.TreeEntity;
import com.groot.namu.tree.repository.TreeRepository;
import com.groot.namu.user.dto.request.CheckCertificationRequestDto;
import com.groot.namu.user.dto.request.EmailCertificationRequestDto;
import com.groot.namu.user.dto.request.EmailCheckRequestDto;
import com.groot.namu.user.dto.request.NicknameCheckRequestDto;
import com.groot.namu.user.dto.request.SignInRequestDto;
import com.groot.namu.user.dto.request.SignUpRequestDto;
import com.groot.namu.user.dto.response.CheckCertificationResponseDto;
import com.groot.namu.user.dto.response.EmailCertificationResponseDto;
import com.groot.namu.user.dto.response.EmailCheckResponseDto;
import com.groot.namu.user.dto.response.NicknameCheckResponseDto;
import com.groot.namu.user.dto.response.SignInResponseDto;
import com.groot.namu.user.dto.response.SignUpResponseDto;
import com.groot.namu.user.entity.CertificationEntity;
import com.groot.namu.user.entity.UserEntity;
import com.groot.namu.user.repository.CertificationRepository;
import com.groot.namu.user.repository.UserRepository;
import com.groot.namu.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final TreeRepository treeRepository;

    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public ResponseEntity<? super EmailCheckResponseDto> emailCheck(EmailCheckRequestDto dto) {
        
        try {

            String email = dto.getEmail();
            boolean isExistId = userRepository.existsByEmail(email);
            if (isExistId) return EmailCheckResponseDto.duplicatedId();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        
        try {

            String email = dto.getEmail();

            //존재하는 이메일인지 확인
            boolean isExistId = userRepository.existsByEmail(email);
            if (isExistId) return EmailCertificationResponseDto.duplicateId();

            //인증번호 생성
            String certificationNumber = getCertificationNumber();

            //메일 전송
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            //메일 전송 결과 저장
            CertificationEntity certificationEntity = new CertificationEntity(email, certificationNumber);
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

            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByEmail(email);
            if (certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched) return CheckCertificationResponseDto.certificationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super NicknameCheckResponseDto> nicknameCheck(NicknameCheckRequestDto dto) {
        
        try {

            String nickname = dto.getNickname();
            boolean isExistId = userRepository.existsByNickname(nickname);
            if (isExistId) return NicknameCheckResponseDto.duplicatedId();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return NicknameCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        
        try {

            String email = dto.getEmail();
            boolean isExistId = userRepository.existsByEmail(email);
            if (isExistId) return SignUpResponseDto.duplicateId();

            String certificationNumber = dto.getCertificationNumber();
            
            CertificationEntity certificationEntity = certificationRepository.findByEmail(email);
            boolean isMatched = 
                certificationEntity.getEmail().equals(email) && 
                certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched) return SignUpResponseDto.certificationFail();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);
            TreeEntity treeEntity = new TreeEntity(email);
            treeRepository.save(treeEntity);

            //certificationRepository.delete(certificationEntity);
            certificationRepository.deleteByEmail(email);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;
        UserEntity userEntity;
        String email;

        try {
            
            email = dto.getEmail();
            userEntity = userRepository.findByEmail(email);
            if ( userEntity == null ) return SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return SignInResponseDto.signInFail();

            token = jwtProvider.create(email);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token, userEntity);
    }
}
