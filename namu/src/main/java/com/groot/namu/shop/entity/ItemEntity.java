package com.groot.namu.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name="item")
@Table(name="item")
public class ItemEntity {
    @Id
    @GeneratedValue
    private int itemId;
    private String itemName;
    private int price;
    private String type;
}
