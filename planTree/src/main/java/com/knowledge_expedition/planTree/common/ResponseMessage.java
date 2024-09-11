package com.knowledge_expedition.planTree.common;

public interface ResponseMessage {
    String SUCCESS = "success";

    String VALIDATION_FAIL = "validation failed";
    String DUPLICATE_ID = "duplicate id";
    
    String SIGN_IN_FAIL = "login informaion mismatch";
    String CERTIFRCATION_FAIL = "certification failed";

    String DATABASE_ERROR = "database error";
    
}
