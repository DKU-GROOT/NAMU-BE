package com.groot.namu.shop.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.groot.namu.global.dto.ResponseDto;
import com.groot.namu.shop.dto.request.AddItemListRequestDto;
import com.groot.namu.shop.dto.request.BuyItemRequestDto;
import com.groot.namu.shop.dto.request.EquipItemRequestDto;
import com.groot.namu.shop.dto.request.UnEquipItemRequestDto;
import com.groot.namu.shop.dto.request.WaterTreeRequestDto;
import com.groot.namu.shop.dto.response.AddItemListResponseDto;
import com.groot.namu.shop.dto.response.BuyItemResponseDto;
import com.groot.namu.shop.dto.response.EquipItemResponseDto;
import com.groot.namu.shop.dto.response.UnEquipItemResponseDto;
import com.groot.namu.shop.dto.response.WaterTreeResponseDto;
import com.groot.namu.shop.entity.ItemEntity;
import com.groot.namu.shop.entity.UserItemEntity;
import com.groot.namu.shop.repository.ItemRepository;
import com.groot.namu.shop.repository.UserItemRepository;
import com.groot.namu.shop.service.ShopService;
import com.groot.namu.tree.entity.TreeEntity;
import com.groot.namu.tree.repository.TreeRepository;
import com.groot.namu.user.entity.UserEntity;
import com.groot.namu.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopServiceImplement implements ShopService{
    private final UserRepository userRepository;
    private final TreeRepository treeRepository;
    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;

    @Override
    public ResponseEntity<? super AddItemListResponseDto> addItemList(AddItemListRequestDto dto) {
        try {
            ItemEntity itemEntity = new ItemEntity(dto);
            itemRepository.save(itemEntity);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return AddItemListResponseDto.success();
    }

    @Override
    public ResponseEntity<? super BuyItemResponseDto> buyItem(BuyItemRequestDto dto) {
        try {
            String email = dto.getEmail();
            String itemName = dto.getItemName();

            ItemEntity itemEntity = itemRepository.findByItemName(itemName);
            if (itemEntity == null) {
                return ResponseDto.databaseError();
            }

            UserItemEntity userItemEntity = new UserItemEntity();
            userItemEntity.setEmail(email);
            userItemEntity.setItemName(itemName);
            userItemEntity.setEquip(false);
            userItemRepository.save(userItemEntity);

            UserEntity userEntity = userRepository.findByEmail(email);
            userEntity.setPoint(userEntity.getPoint() - itemEntity.getPrice());
            userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return BuyItemResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EquipItemResponseDto> equipItem(EquipItemRequestDto dto) {
        try {
            String email = dto.getEmail();
            String itemName = dto.getItemName();

            UserItemEntity userItemEntity = userItemRepository.findByEmailAndItemName(email, itemName);
            if (userItemEntity == null) {
                return ResponseDto.databaseError();
            }

            userItemEntity.setEquip(true);
            userItemRepository.save(userItemEntity);

            ItemEntity itemEntity = itemRepository.findByItemName(itemName);
            if (itemEntity == null) {
                return ResponseDto.databaseError();
            }

            String itemType = itemEntity.getType();
            TreeEntity treeEntity = treeRepository.findByEmail(email);
            if(treeEntity == null) {
                return ResponseDto.databaseError();
            }

            if ("top".equalsIgnoreCase(itemType)){
                treeEntity.setTreeDecoTop(itemName);
            } else if ("bottom".equalsIgnoreCase(itemType)){
                treeEntity.setTreeDecoBottom(itemName);
            } else {
                return ResponseDto.databaseError();
            }

            treeRepository.save(treeEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EquipItemResponseDto.success();
    }

    @Override
    public ResponseEntity<? super UnEquipItemResponseDto> unEquipItem(UnEquipItemRequestDto dto) {
        try {
            String email = dto.getEmail();
            String itemName = dto.getItemName();

            UserItemEntity userItemEntity = userItemRepository.findByEmailAndItemName(email, itemName);
            if (userItemEntity == null) {
                return ResponseDto.databaseError();
            }

            userItemEntity.setEquip(false);
            userItemRepository.save(userItemEntity);

            ItemEntity itemEntity = itemRepository.findByItemName(itemName);
            if (itemEntity == null) {
                return ResponseDto.databaseError();
            }

            String itemType = itemEntity.getType();
            TreeEntity treeEntity = treeRepository.findByEmail(email);
            if(treeEntity == null) {
                return ResponseDto.databaseError();
            }

            if ("top".equalsIgnoreCase(itemType)){
                treeEntity.setTreeDecoTop(null);
            } else if ("bottom".equalsIgnoreCase(itemType)){
                treeEntity.setTreeDecoBottom(null);
            } else {
                return ResponseDto.databaseError();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return UnEquipItemResponseDto.success();
    }

    @Override
    public ResponseEntity<? super WaterTreeResponseDto> waterTree(WaterTreeRequestDto dto) {

        try{
            String email = dto.getEmail();

            UserEntity userEntity = userRepository.findByEmail(email);
            TreeEntity treeEntity = treeRepository.findByEmail(email);

            if(userEntity == null || treeEntity == null){
                return ResponseDto.databaseError();
            }

            int userPoint = userEntity.getPoint();
            int point = treeEntity.getTreePoint();
            if (userPoint >= point) {
                userEntity.setPoint(userPoint - point);
                userRepository.save(userEntity);

                treeEntity.setTreeLevel(treeEntity.getTreeLevel() + 1);
                treeEntity.setTreePoint(treeEntity.getTreePoint() + 10);
                treeRepository.save(treeEntity);
            }
            else{
                return ResponseDto.databaseError();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return WaterTreeResponseDto.success();
    }
    
}
