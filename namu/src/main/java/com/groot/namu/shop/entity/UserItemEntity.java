package com.groot.namu.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="user_item")
@Table(name="user_item")
public class UserItemEntity {
    @Id
    @GeneratedValue
    private int userItemId;
    private String email;
    private String itemName;
    private boolean equip;
}
