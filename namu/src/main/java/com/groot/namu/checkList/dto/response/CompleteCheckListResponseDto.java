package com.groot.namu.checkList.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

public class CompleteCheckListResponseDto extends ResponseDto{
    private CompleteCheckListResponseDto () {
        super();
    }    

    public static ResponseEntity<CompleteCheckListResponseDto> success() {
        CompleteCheckListResponseDto responseBody = new CompleteCheckListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
