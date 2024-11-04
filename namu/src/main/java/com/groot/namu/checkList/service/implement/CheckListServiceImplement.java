package com.groot.namu.checkList.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.groot.namu.checkList.dto.request.AddCheckListRequestDto;
import com.groot.namu.checkList.dto.response.AddCheckListResponseDto;
import com.groot.namu.checkList.entity.CheckListEntity;
import com.groot.namu.checkList.repository.CheckListRepository;
import com.groot.namu.checkList.service.CheckListService;
import com.groot.namu.global.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckListServiceImplement implements CheckListService{

    private final CheckListRepository checkListRepository;

    @Override
    public ResponseEntity<? super AddCheckListResponseDto> addCheckList(AddCheckListRequestDto dto) {
        try {
            String email = dto.getEmail();
            String date = dto.getDate();
            String checkList = dto.getCheckList();

            CheckListEntity checkListEntity = checkListRepository.findByEmailandDate(email, date);

            if (checkListEntity == null){
                CheckListEntity newCheckListEntity = new CheckListEntity();
                newCheckListEntity.setEmail(email);
                newCheckListEntity.setDate(date);
                newCheckListEntity.setCheckList1(checkList);
                checkListRepository.save(newCheckListEntity);
            } else {
                if (checkListEntity.getCheckList2() == null){
                    checkListEntity.setCheckList2(checkList);
                    checkListRepository.save(checkListEntity);
                } else if (checkListEntity.getCheckList3() == null) {
                    checkListEntity.setCheckList3(checkList);
                    checkListRepository.save(checkListEntity);
                } else if (checkListEntity.getCheckList4() == null) {
                    checkListEntity.setCheckList4(checkList);
                    checkListRepository.save(checkListEntity);
                } else if (checkListEntity.getCheckList5() == null) {
                    checkListEntity.setCheckList5(checkList);
                    checkListRepository.save(checkListEntity);
                } else {
                    return ResponseDto.databaseError();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return AddCheckListResponseDto.success();
    }
    
}
