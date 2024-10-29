package com.groot.namu.study.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.groot.namu.study.common.ResponseCode;
import com.groot.namu.study.common.ResponseMessage;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
public class SummaryResponseDto extends ResponseDto{
    @Column(columnDefinition = "LONGTEXT")
    private String summary;

    public SummaryResponseDto() {
        this.summary="";
    }

    @JsonCreator
    public SummaryResponseDto(@JsonProperty("summary") String summary) {
        this.summary = summary;
    }

    private List<ChatResponseDto.Choice> choices;

    @Getter
    @Setter
    public static class Choice {
        private ChatResponseDto.Message message;
        @JsonProperty("finish_reason")
        private String finish_reason;
        private int index;
    }

    @Setter
    @Getter
    public static class Message {
        private String role;
        private String content;
    }

    public static ResponseEntity<SummaryResponseDto> success(String summaryText) {
        SummaryResponseDto responseBody = new SummaryResponseDto(summaryText);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> summaryFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SUMMARY_FAIL, ResponseMessage.SUMMARY_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
