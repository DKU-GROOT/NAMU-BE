package com.groot.namu.checkList.service;

import org.springframework.http.ResponseEntity;

import com.groot.namu.checkList.dto.request.*;
import com.groot.namu.checkList.dto.response.*;

public interface CheckListService {
    ResponseEntity<? super AddCheckListResponseDto> addCheckList(AddCheckListRequestDto dto);
    ResponseEntity<? super CompleteCheckListResponseDto> completeCheckList(CompleteCheckListRequestDto dto);
    ResponseEntity<? super DeleteCheckListResponseDto> deleteCheckList(DeleteCheckListRequestDto dto);
    ResponseEntity<? super CheckCheckListResponseDto> checkCheckList(CheckCheckListRequestDto dto);
}
