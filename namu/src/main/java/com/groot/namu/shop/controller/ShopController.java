package com.groot.namu.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groot.namu.shop.dto.request.*;
import com.groot.namu.shop.dto.response.*;
import com.groot.namu.shop.service.ShopService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/namu/v2/shop")
public class ShopController {
    
    private final ShopService shopService;

    @PostMapping("/add-item")
    public ResponseEntity<? super AddItemListResponseDto> addItem(
        @RequestBody @Valid AddItemListRequestDto requestBody
    ) {
        ResponseEntity<? super AddItemListResponseDto> response = shopService.addItemList(requestBody);    
        return response;
    }

    @PostMapping("/buy-item")
    public ResponseEntity<? super BuyItemResponseDto> buyItem(
        @RequestBody @Valid BuyItemRequestDto requestBody
    ) {
        ResponseEntity<? super BuyItemResponseDto> response = shopService.buyItem(requestBody);    
        return response;
    }

    @PostMapping("/equip")
    public ResponseEntity<? super EquipItemResponseDto> equipItem(
        @RequestBody @Valid EquipItemRequestDto requestBody
    ) {
        ResponseEntity<? super EquipItemResponseDto> response = shopService.equipItem(requestBody);    
        return response;
    }

    @PostMapping("/unequip")
    public ResponseEntity<? super UnEquipItemResponseDto> unEquipItem(
        @RequestBody @Valid UnEquipItemRequestDto requestBody
    ) {
        ResponseEntity<? super UnEquipItemResponseDto> response = shopService.unEquipItem(requestBody);    
        return response;
    }
    

    @PostMapping("/water")
    public ResponseEntity<? super WaterTreeResponseDto> waterTree(
        @RequestBody @Valid WaterTreeRequestDto requestBody
    ) {
        ResponseEntity<? super WaterTreeResponseDto> response = shopService.waterTree(requestBody);
        return response;
    }
}
