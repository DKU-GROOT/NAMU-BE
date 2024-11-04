package com.groot.namu.checkList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.checkList.entity.CheckListEntity;

public interface CheckListRepository extends JpaRepository<CheckListEntity, Long>{
    CheckListEntity findByEmailandDate(String email, String date);
    CheckListEntity existsByEmailandDate(String email, String date);
}
