package com.groot.namu.checkList.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

public class AddCheckListResponseDto extends ResponseDto{
    private AddCheckListResponseDto () {
        super();
    }

    public static ResponseEntity<AddCheckListResponseDto> success() {
        AddCheckListResponseDto responseBody = new AddCheckListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
