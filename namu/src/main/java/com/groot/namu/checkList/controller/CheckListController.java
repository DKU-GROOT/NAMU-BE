package com.groot.namu.checkList.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groot.namu.checkList.dto.request.AddCheckListRequestDto;
import com.groot.namu.checkList.dto.response.AddCheckListResponseDto;
import com.groot.namu.checkList.service.CheckListService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/namu/v2/check-list")
public class CheckListController {
    
    private final CheckListService checkListService;

    @PostMapping("/add")
    public ResponseEntity<? super AddCheckListResponseDto> addCheckList(
        @RequestBody @Valid AddCheckListRequestDto requestBody
    ) {
        ResponseEntity<? super AddCheckListResponseDto> response = checkListService.addCheckList(requestBody);

        return response;
    }
    
}