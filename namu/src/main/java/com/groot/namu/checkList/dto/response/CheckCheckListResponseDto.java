package com.groot.namu.checkList.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.groot.namu.checkList.entity.CheckListEntity;
import com.groot.namu.global.dto.ResponseDto;

import lombok.Getter;

@Getter
public class CheckCheckListResponseDto extends ResponseDto{

    private String checkList1;
    private boolean checkList1Complete;
    private String checkList2;
    private boolean checkList2Complete;
    private String checkList3;
    private boolean checkList3Complete;
    private String checkList4;
    private boolean checkList4Complete;
    private String checkList5;
    private boolean checkList5Complete;

    private CheckCheckListResponseDto(CheckListEntity entity) {
        super();
        this.checkList1 = entity.getCheckList1();
        this.checkList1Complete = entity.isCheckList1Complete();
        this.checkList2 = entity.getCheckList2();
        this.checkList2Complete = entity.isCheckList2Complete();
        this.checkList3 = entity.getCheckList3();
        this.checkList3Complete = entity.isCheckList3Complete();
        this.checkList4 = entity.getCheckList4();
        this.checkList4Complete = entity.isCheckList4Complete();
        this.checkList5 = entity.getCheckList5();
        this.checkList5Complete = entity.isCheckList5Complete();
    }

    public static ResponseEntity<CheckCheckListResponseDto> success(CheckListEntity entity) {
        CheckCheckListResponseDto responseBody = new CheckCheckListResponseDto(entity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
