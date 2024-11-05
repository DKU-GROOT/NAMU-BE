package com.groot.namu.checkList.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.groot.namu.checkList.dto.request.AddCheckListRequestDto;
import com.groot.namu.checkList.dto.request.CheckCheckListRequestDto;
import com.groot.namu.checkList.dto.request.CompleteCheckListRequestDto;
import com.groot.namu.checkList.dto.request.DeleteCheckListRequestDto;
import com.groot.namu.checkList.dto.response.AddCheckListResponseDto;
import com.groot.namu.checkList.dto.response.CheckCheckListResponseDto;
import com.groot.namu.checkList.dto.response.CompleteCheckListResponseDto;
import com.groot.namu.checkList.dto.response.DeleteCheckListResponseDto;
import com.groot.namu.checkList.entity.CheckListEntity;
import com.groot.namu.checkList.repository.CheckListRepository;
import com.groot.namu.checkList.service.CheckListService;
import com.groot.namu.global.dto.ResponseDto;
import com.groot.namu.user.entity.UserEntity;
import com.groot.namu.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckListServiceImplement implements CheckListService{

    private final CheckListRepository checkListRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super AddCheckListResponseDto> addCheckList(AddCheckListRequestDto dto) {
        try {
            String email = dto.getEmail();
            String date = dto.getDate();
            String checkList = dto.getCheckList();

            CheckListEntity checkListEntity = checkListRepository.findByEmailAndDate(email, date);

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

    @Override
    public ResponseEntity<? super CompleteCheckListResponseDto> completeCheckList(CompleteCheckListRequestDto dto) {
        try {
            String email = dto.getEmail();
            String date = dto.getDate();
            int num = dto.getNum();

            CheckListEntity checkListEntity = checkListRepository.findByEmailAndDate(email, date);
            UserEntity userEntity = userRepository.findByEmail(email);

            if (checkListEntity == null) {
                return ResponseDto.databaseError();
            } else if( num == 1) {
                checkListEntity.setCheckList1Complete(true);
            } else if( num == 2) {
                checkListEntity.setCheckList2Complete(true);
            }else if( num == 3) {
                checkListEntity.setCheckList3Complete(true);
            }else if( num == 4) {
                checkListEntity.setCheckList4Complete(true);
            }else if( num == 5) {
                checkListEntity.setCheckList5Complete(true);
            } else{
                return ResponseDto.databaseError();
            } 

            userEntity.setPoint(userEntity.getPoint() + 3);
            checkListRepository.save(checkListEntity);
            userRepository.save(userEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CompleteCheckListResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteCheckListResponseDto> deleteCheckList(DeleteCheckListRequestDto dto) {
        try {
            String email = dto.getEmail();
            String date = dto.getDate();
            int num = dto.getNum();

            CheckListEntity checkListEntity = checkListRepository.findByEmailAndDate(email, date);
            if (checkListEntity == null){
                return ResponseDto.databaseError();
            } else if (num == 1){
                checkListEntity.setCheckList1(checkListEntity.getCheckList2());
                checkListEntity.setCheckList1Complete(checkListEntity.isCheckList2Complete());
                checkListEntity.setCheckList2(checkListEntity.getCheckList3());
                checkListEntity.setCheckList2Complete(checkListEntity.isCheckList3Complete());
                checkListEntity.setCheckList3(checkListEntity.getCheckList4());
                checkListEntity.setCheckList3Complete(checkListEntity.isCheckList4Complete());
                checkListEntity.setCheckList4(checkListEntity.getCheckList5());
                checkListEntity.setCheckList4Complete(checkListEntity.isCheckList5Complete());
                checkListEntity.setCheckList5(null);
                checkListEntity.setCheckList5Complete(false);
            } else if (num == 2){
                checkListEntity.setCheckList2(checkListEntity.getCheckList3());
                checkListEntity.setCheckList2Complete(checkListEntity.isCheckList3Complete());
                checkListEntity.setCheckList3(checkListEntity.getCheckList4());
                checkListEntity.setCheckList3Complete(checkListEntity.isCheckList4Complete());
                checkListEntity.setCheckList4(checkListEntity.getCheckList5());
                checkListEntity.setCheckList4Complete(checkListEntity.isCheckList5Complete());
                checkListEntity.setCheckList5(null);
                checkListEntity.setCheckList5Complete(false);
            } else if (num == 3){
                checkListEntity.setCheckList3(checkListEntity.getCheckList4());
                checkListEntity.setCheckList3Complete(checkListEntity.isCheckList4Complete());
                checkListEntity.setCheckList4(checkListEntity.getCheckList5());
                checkListEntity.setCheckList4Complete(checkListEntity.isCheckList5Complete());
                checkListEntity.setCheckList5(null);
                checkListEntity.setCheckList5Complete(false);
            } else if (num == 4){
                checkListEntity.setCheckList4(checkListEntity.getCheckList5());
                checkListEntity.setCheckList4Complete(checkListEntity.isCheckList5Complete());
                checkListEntity.setCheckList5(null);
                checkListEntity.setCheckList5Complete(false);
            } else if (num == 5){
                checkListEntity.setCheckList5(null);
                checkListEntity.setCheckList5Complete(false);
            }

            checkListRepository.save(checkListEntity);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteCheckListResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCheckListResponseDto> checkCheckList(CheckCheckListRequestDto dto) {
        String email = dto.getEmail();
        String date = dto.getDate();

        CheckListEntity checkListEntity = checkListRepository.findByEmailAndDate(email, date);
        if (checkListEntity == null) {
            return ResponseDto.databaseError();   
        }
        return CheckCheckListResponseDto.success(checkListEntity);
    }
    
}
