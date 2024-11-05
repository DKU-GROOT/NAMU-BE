package com.groot.namu.user.common;

import com.groot.namu.global.common.ResponseMessage;

public interface UserResponseMessage extends ResponseMessage{
    String MAIL_FAIL = "Mail send failed";
    String USER_NOT_EXISTS = "user is not exists";
    String DUPLICATE_EMAIL = "duplicated email";
    String DUPLICATE_ID = "duplicate id";
    String ENTITY_NOT_FOND = "entity not found";
    String SIGN_IN_FAIL = "login informaion mismatch";
    String CERTIFICATION_FAIL = "certification failed";
}
