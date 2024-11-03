package com.groot.namu.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.shop.entity.UserItemEntity;

public interface UserItemRepository extends JpaRepository<UserItemEntity, Integer>{
    UserItemEntity findByEmailAndItemName(String email, String itemName);
}
