package com.groot.namu.study.dto.response;

import com.groot.namu.study.common.StudyResponseCode;
import com.groot.namu.study.common.StudyResponseMessage;
import com.groot.namu.study.entity.SubjectEntity;
//import com.groot.namu.study.entity.UserSubjectEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
public class ShowMyStudyResponseDto extends ResponseDto{
    private List<SubjectEntity> subjectList;

    public static ResponseEntity<ShowMyStudyResponseDto> success(List<SubjectEntity> subjectList) {
        ShowMyStudyResponseDto responseBody = new ShowMyStudyResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> loadFail() {
        ResponseDto responseBody = new ResponseDto(StudyResponseCode.LOAD_FAIL, StudyResponseMessage.LOAD_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
