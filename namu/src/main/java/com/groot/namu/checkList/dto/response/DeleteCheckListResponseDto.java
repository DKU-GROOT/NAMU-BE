package com.groot.namu.checkList.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.global.dto.ResponseDto;

public class DeleteCheckListResponseDto extends ResponseDto{
    private DeleteCheckListResponseDto () {
        super();
    }

    public static ResponseEntity<DeleteCheckListResponseDto> success() {
        DeleteCheckListResponseDto responseBody = new DeleteCheckListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
