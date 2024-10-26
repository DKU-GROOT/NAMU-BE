package com.groot.namu.user.service;

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
}
