package com.groot.namu.shop.entity;

import com.groot.namu.shop.dto.request.AddItemListRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="item")
@Table(name="item")
public class ItemEntity {
    @Id
    private String itemName;
    private int price;
    private String type;

    public ItemEntity(AddItemListRequestDto dto){
        this.itemName = dto.getItemName();
        this.price = dto.getPrice();
        this.type = dto.getType();
    }
}
