package com.groot.namu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groot.namu.entity.ExamEntity;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    
}
