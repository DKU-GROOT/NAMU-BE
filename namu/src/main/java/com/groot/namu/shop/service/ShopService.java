package com.groot.namu.shop.service;

import org.springframework.http.ResponseEntity;

import com.groot.namu.shop.dto.request.*;
import com.groot.namu.shop.dto.response.*;

public interface ShopService {
    ResponseEntity<? super AddItemListResponseDto> addItemList(AddItemListRequestDto dto);
    ResponseEntity<? super BuyItemResponseDto> buyItem(BuyItemRequestDto dto);
    ResponseEntity<? super EquipItemResponseDto> equipItem(EquipItemRequestDto dto);
    ResponseEntity<? super UnEquipItemResponseDto> unEquipItem (UnEquipItemRequestDto dto);
    ResponseEntity<? super WaterTreeResponseDto> waterTree (WaterTreeRequestDto dto);
}
