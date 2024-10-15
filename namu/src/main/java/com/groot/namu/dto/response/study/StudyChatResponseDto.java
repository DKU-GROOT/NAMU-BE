package com.groot.namu.dto.response.study;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.common.ResponseCode;
import com.groot.namu.common.ResponseMessage;
import com.groot.namu.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class StudyChatResponseDto extends ResponseDto {

    private String answer;

    private StudyChatResponseDto(String answer) {
        super();
        this.answer = answer;
    }

    public static ResponseEntity<StudyChatResponseDto> success(String answer) {
        StudyChatResponseDto responseBody = new StudyChatResponseDto(answer);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> questionFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.QUESTION_FAIL, ResponseMessage.QUESTION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
