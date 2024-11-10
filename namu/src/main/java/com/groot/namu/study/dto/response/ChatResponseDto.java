package com.groot.namu.study.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.groot.namu.global.dto.ResponseDto;
import com.groot.namu.study.common.StudyResponseCode;
import com.groot.namu.study.common.StudyResponseMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
public class ChatResponseDto extends ResponseDto {

    private final String answer;

    public ChatResponseDto() {
        this.answer = "";
    }
    @JsonCreator
    public ChatResponseDto(@JsonProperty("answer") String answer) {
        this.answer = answer;
    }

    private List<Choice> choices;

    @Getter
    @Setter
    public static class Choice {
        private Message message;
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

    public static ResponseEntity<ChatResponseDto> success(String answer) {
        ChatResponseDto responseBody = new ChatResponseDto(answer);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> questionFail() {
        ResponseDto responseBody = new ResponseDto(StudyResponseCode.QUESTION_FAIL, StudyResponseMessage.QUESTION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
