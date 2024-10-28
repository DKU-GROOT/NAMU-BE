package com.groot.namu.user.service;

import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groot.namu.tree.entity.Tree;
import com.groot.namu.tree.repository.TreeRepository;
import com.groot.namu.user.entity.User;
import com.groot.namu.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TreeRepository treeRepository;

    @Transactional
    public User registerUser(String email, String nickname) {
        User user = new User(email, nickname);
        userRepository.save(user);
        
        Tree tree = new Tree(user);
        treeRepository.save(tree);

        return user;
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean login(OAuth2User oAuth2User) {
        // OAuth2User에서 이메일 정보 추출
        String email = oAuth2User.getAttribute("email");

        if (email == null) {
            throw new IllegalArgumentException("Email not found in OAuth2User attributes.");
        }

        // 데이터베이스에서 이메일로 사용자 검색
        Optional<User> userOptional = userRepository.findByEmail(email);

        // 사용자가 존재하는지 확인하여 로그인 처리
        return userOptional.isPresent();
    }
}
