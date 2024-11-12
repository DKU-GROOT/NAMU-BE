package com.groot.namu.user.common;

import com.groot.namu.global.common.ResponseCode;

public interface UserResponseCode extends ResponseCode{
    String MAIL_FAIL = "MF";
    String USER_NOT_EXISTS = "UNE";
    String ENTITY_NOT_FOND = "ENF";
    String CERTIFICATION_FAIL = "CF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_ID = "DI";
    String SIGN_IN_FAIL = "SF";
    String DUPLICATE_NICKNAME = "DN";
}
