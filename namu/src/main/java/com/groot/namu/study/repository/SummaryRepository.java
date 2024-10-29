package com.groot.namu.study.repository;

import com.groot.namu.study.entity.SummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SummaryRepository extends JpaRepository<SummaryEntity, String> {
    Optional<SummaryEntity> findByEmailAndSubjectName(String email, String subjectName);
}
