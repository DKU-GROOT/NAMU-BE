package com.groot.namu.global.common;

public interface ResponseCode {
    String SUCCESS = "SU";

    String VALIDATION_FAIL = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_ID = "DI";
    
    String SIGN_IN_FAIL = "SF";
    String CERTIFRCATION_FAIL = "CF";
    String QUESTION_FAIL = "QF";

    String MAIL_FAIL = "MF";
    String DATABASE_ERROR = "DBE";

    String USER_NOT_EXISTS = "UNE";

    String ENTITY_NOT_FOND = "ENF";

    String SUMMARY_FAIL = "SF";
    String EXAM_FAIL = "EF";
}
