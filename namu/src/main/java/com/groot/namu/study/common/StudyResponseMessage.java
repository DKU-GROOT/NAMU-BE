package com.groot.namu.study.common;

import com.groot.namu.global.common.ResponseMessage;

public interface StudyResponseMessage extends ResponseMessage{
    String SUMMARY_FAIL = "making summary failed";
    String EXAM_FAIL = "making exam failed";
    String QUESTION_FAIL = "question failed";
}
