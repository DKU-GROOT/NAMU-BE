package com.groot.namu.global.provider;

import java.util.Map;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.groot.namu.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2Provider {
    private final UserRepository userRepository;

    public String getEmailFromOAuth2(OAuth2AuthenticationToken authentication) {
        OAuth2User oauthUser = authentication.getPrincipal();
        
        // 카카오와 네이버의 이메일 필드가 다를 수 있으므로 분기 처리
        String email = null;
        if (authentication.getAuthorizedClientRegistrationId().equals("kakao")) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) oauthUser.getAttributes().get("kakao_account");
            if (kakaoAccount != null) {
                email = (String) kakaoAccount.get("email");
            }
        } else if (authentication.getAuthorizedClientRegistrationId().equals("naver")) {
            Map<String, Object> response = (Map<String, Object>) oauthUser.getAttributes().get("response");
            if (response != null) {
                email = (String) response.get("email");
            }
        }

        return email;
    }

    public boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
