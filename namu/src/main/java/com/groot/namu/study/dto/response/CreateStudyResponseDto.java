package com.groot.namu.study.dto.response;

import com.groot.namu.global.dto.ResponseDto;
import com.groot.namu.study.common.StudyResponseCode;
import com.groot.namu.study.common.StudyResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class CreateStudyResponseDto extends ResponseDto {
    public static ResponseEntity<CreateStudyResponseDto> success() {
        CreateStudyResponseDto responseBody = new CreateStudyResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> createFail() {
        ResponseDto responseBody = new ResponseDto(StudyResponseCode.CREATE_FAIL, StudyResponseMessage.CREATE_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
