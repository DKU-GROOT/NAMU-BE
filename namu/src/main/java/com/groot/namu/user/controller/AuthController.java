package com.groot.namu.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.groot.namu.user.dto.request.SignUpRequestDto;
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
    public String registerUser(SignUpRequestDto signUpRequestDto) {
        userService.registerUser(signUpRequestDto.getEmail(), signUpRequestDto.getNickname());
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
