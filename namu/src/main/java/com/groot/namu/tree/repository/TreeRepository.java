package com.groot.namu.tree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.tree.entity.Tree;

public interface TreeRepository extends JpaRepository<Tree, String>{
    
}
