package com.groot.namu.study.repository;

import com.groot.namu.study.entity.UserQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuestionRepository extends JpaRepository<UserQuestionEntity, Long> {
    List<UserQuestionEntity> findByEmailAndSubjectName(String email, String subjectName);
}
