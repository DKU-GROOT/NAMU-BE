package com.groot.namu.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.groot.namu.user.dto.request.SignUpRequestDto;
import com.groot.namu.user.dto.response.LoginResponseDto;
import com.groot.namu.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> registerUser(SignUpRequestDto signUpRequestDto) {
        if (userService.isEmailExists(signUpRequestDto.getEmail())) {
             return LoginResponseDto.validationFail(); // 중복 이메일
        }
        try {
            userService.registerUser(signUpRequestDto.getEmail(), signUpRequestDto.getNickname());
            return ResponseEntity.ok(new LoginResponseDto()); // 성공 응답
        } catch (Exception e) {
            return LoginResponseDto.databaseError(); // 데이터베이스 오류
        }
    }

    @GetMapping("/oauth2/login")
    public ResponseEntity<LoginResponseDto> loginUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        // OAuth2User의 이메일 기반 로그인 처리
        if (!userService.login(oAuth2User)) {
            return ResponseEntity.status(401)
                    .body(new LoginResponseDto("SF", "Login failed. User not found."));
        }

        return ResponseEntity.ok(new LoginResponseDto());
    }
}
