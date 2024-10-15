package com.groot.namu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groot.namu.entity.SummaryEntity;

import java.util.Optional;

@Repository
public interface SummaryRepository extends JpaRepository<SummaryEntity, Long> {
    // 특정 email과 subject_id로 Summary 검색
    Optional<SummaryEntity> findByEmailAndSubjectName(String email, String subjectName);
}
