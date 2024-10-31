package com.groot.namu.tree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.tree.entity.TreeEntity;

public interface TreeRepository extends JpaRepository<TreeEntity, String>{
    boolean existsByEmail(String email);
    TreeEntity findByEmail(String email);
}
