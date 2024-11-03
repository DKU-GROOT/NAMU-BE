package com.groot.namu.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groot.namu.shop.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer>{
    boolean ExistsByName(String itemName);
    ItemEntity findByName(String itemName);
}
