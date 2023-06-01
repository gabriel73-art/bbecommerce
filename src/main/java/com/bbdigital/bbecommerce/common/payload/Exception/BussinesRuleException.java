package com.bbdigital.bbecommerce.common.payload.Exception;

public class BussinesRuleException extends Exception {
    private long id;
    private String code;

    public BussinesRuleException(long id, String code,String message) {
        super(message);
        this.id = id;
        this.code = code;
    }
    public BussinesRuleException(String code,String message) {
        super(message);
        this.code = code;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

