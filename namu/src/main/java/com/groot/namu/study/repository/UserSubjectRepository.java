package com.groot.namu.study.repository;

import com.groot.namu.study.entity.UserSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubjectRepository extends JpaRepository<UserSubjectEntity, Long> {

}
