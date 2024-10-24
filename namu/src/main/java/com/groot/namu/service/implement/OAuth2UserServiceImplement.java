package com.groot.namu.service.implement;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.groot.namu.entity.CustomOAuth2User;
import com.groot.namu.entity.UserEntity;
import com.groot.namu.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        UserEntity userEntity = null;
        String email = null;
        Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));// 필요한 권한 목록을 설정합니다.


        if (oauthClientName.equals("kakao")) {
            // Kakao OAuth2 응답에서 'kakao_account' 객체를 가져옴
            Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");

            // 이메일을 kakao_account에서 가져옴
            if (kakaoAccount != null && kakaoAccount.containsKey("email")) {
                email = (String) kakaoAccount.get("email");
            }

            // 이메일이 없을 경우의 기본값 설정 (선택사항)
            if (email == null) {
                throw new OAuth2AuthenticationException("Kakao 계정에서 이메일 정보를 찾을 수 없습니다.");
            }

            // userEntity 생성 (사용자 이메일만 저장)
            userEntity = new UserEntity(email, "kakao");
        }

        if (oauthClientName.equals("naver")) {
            // Naver OAuth2 응답에서 'response' 객체를 가져옴
            Map<String, String> responseMap = (Map<String, String>)oAuth2User.getAttributes().get("response");
            email = responseMap.get("email");

            if (email == null) {
                throw new OAuth2AuthenticationException("Naver 계정에서 이메일 정보를 찾을 수 없습니다.");
            }

            // userEntity 생성 (사용자 이메일만 저장)
            userEntity = new UserEntity(email, "naver");
        }

        // 사용자 정보를 저장 (이미 존재할 경우 덮어쓰기 로직 추가 가능)
        userRepository.save(userEntity);

        return new CustomOAuth2User(oAuth2User.getAttributes(), email, authorities);  // email을 userId 대신 사용
    }
}
