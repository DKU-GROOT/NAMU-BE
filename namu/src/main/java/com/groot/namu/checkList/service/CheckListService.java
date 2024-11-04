package com.groot.namu.checkList.service;

import org.springframework.http.ResponseEntity;

import com.groot.namu.checkList.dto.request.AddCheckListRequestDto;
import com.groot.namu.checkList.dto.response.AddCheckListResponseDto;

public interface CheckListService {
    ResponseEntity<? super AddCheckListResponseDto> addCheckList(AddCheckListRequestDto dto);
}
