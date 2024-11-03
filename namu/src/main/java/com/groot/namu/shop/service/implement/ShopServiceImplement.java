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

    @Override
    public ResponseEntity<? super AddItemListResponseDto> addItemList(AddItemListRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemList'");
    }

    @Override
    public ResponseEntity<? super BuyItemResponseDto> buyItem(BuyItemRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyItem'");
    }

    @Override
    public ResponseEntity<? super EquipItemResponseDto> equipItem(EquipItemRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equipItem'");
    }

    @Override
    public ResponseEntity<? super UnEquipItemResponseDto> unEquipItem(UnEquipItemRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unEquipItem'");
    }

    @Override
    public ResponseEntity<? super WaterTreeResponseDto> waterTree(WaterTreeRequestDto dto) {

        try{
            String email = dto.getEmail();
            boolean isExistId = userRepository.existsByEmail(email);
            if (isExistId) return WaterTreeResponseDto.databaseError();

            UserEntity userEntity = userRepository.findByEmail(email);
            TreeEntity treeEntity = treeRepository.findByEmail(email);

            if(userEntity == null || treeEntity == null){
                return WaterTreeResponseDto.entityNotFound();
            }

            int userPoint = userEntity.getPoint();
            int point = dto.getPoint();
            if (userPoint >= point) {
                userEntity.setPoint(userPoint - point);
                userRepository.save(userEntity);

                treeEntity.setTreeLevel(treeEntity.getTreeLevel() + 1);
                treeEntity.setTreePoint(treeEntity.getTreePoint() + 10);
                treeRepository.save(treeEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return WaterTreeResponseDto.success();
    }
    
}
