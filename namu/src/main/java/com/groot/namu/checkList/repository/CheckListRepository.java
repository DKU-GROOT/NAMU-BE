package com.groot.namu.checkList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.checkList.entity.CheckListEntity;

public interface CheckListRepository extends JpaRepository<CheckListEntity, Long>{
    CheckListEntity findByEmailAndDate(String email, String date);
    CheckListEntity existsByEmailAndDate(String email, String date);
}
