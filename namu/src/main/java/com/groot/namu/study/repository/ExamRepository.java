package com.groot.namu.study.repository;

import com.groot.namu.study.entity.ExamEntity;
import com.groot.namu.study.entity.UserQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    ExamEntity findByExamId(int exam_id);
}
