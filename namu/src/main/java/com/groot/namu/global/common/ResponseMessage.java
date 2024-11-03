package com.groot.namu.global.common;

public interface ResponseMessage {
    String SUCCESS = "success";

    String VALIDATION_FAIL = "validation failed";
    String DUPLICATE_EMAIL = "duplicated email";
    String DUPLICATE_ID = "duplicate id";
    String ENTITY_NOT_FOND = "entity not found";
    
    String SIGN_IN_FAIL = "login informaion mismatch";
    String CERTIFRCATION_FAIL = "certification failed";
    String QUESTION_FAIL = "question failed";

    String MAIL_FAIL = "Mail send failed";
    String DATABASE_ERROR = "database error";

    String USER_NOT_EXISTS = "user is not exists";
}
