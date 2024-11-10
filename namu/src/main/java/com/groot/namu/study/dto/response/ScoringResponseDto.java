package com.groot.namu.study.dto.response;

import com.groot.namu.study.common.StudyResponseCode;
import com.groot.namu.study.common.StudyResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
public class ScoringResponseDto extends ResponseDto{
    private int score;
    private String[] result;


    public static ResponseEntity<ScoringResponseDto> success(int score, String[] result) {
        ScoringResponseDto responseBody = new ScoringResponseDto(score, result);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> scoringFail() {
        ResponseDto responseBody = new ResponseDto(StudyResponseCode.SCORING_FAIL, StudyResponseMessage.SCORING_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
