package com.groot.namu.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.shop.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, String>{
    boolean existsByItemName(String itemName);
    ItemEntity findByItemName(String itemName);
}
