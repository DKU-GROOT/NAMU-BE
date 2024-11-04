package com.groot.namu.study.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.groot.namu.global.common.ResponseCode;
import com.groot.namu.global.common.ResponseMessage;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponseDto extends ResponseDto{

    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("quiz1")
    private String quiz1;

    @Column(columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String solution1;

    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("quiz2")
    private String quiz2;

    @Column(columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String solution2;

    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("quiz3")
    private String quiz3;

    @Column(columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String solution3;

    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("quiz4")
    private String quiz4;

    @Column(columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String solution4;

    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty("quiz5")
    private String quiz5;

    @Column(columnDefinition = "LONGTEXT")
    @JsonIgnore
    private String solution5;


    private List<ChatResponseDto.Choice> choices;

    public static class Choice {
        @JsonProperty("finish_reason")
        private String finish_reason;
    }

    public static ResponseEntity<ExamResponseDto> success(String quiz1, String quiz2, String quiz3, String quiz4, String quiz5) {
        ExamResponseDto responseBody = new ExamResponseDto();
        responseBody.setQuiz1(quiz1);
        responseBody.setQuiz2(quiz2);
        responseBody.setQuiz3(quiz3);
        responseBody.setQuiz4(quiz4);
        responseBody.setQuiz5(quiz5);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> examFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.EXAM_FAIL, ResponseMessage.EXAM_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}