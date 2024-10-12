package com.groot.namu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groot.namu.entity.TreeEntity;

@Repository
public interface TreeRepository extends JpaRepository<TreeEntity, String>{

    boolean existsByEmail(String email);

    TreeEntity findByEmail(String Email);
    
} 