package com.bbdigital.bbecommerce.common.payload.Response;

import com.bbdigital.bbecommerce.Utils.Constants;

public class ErrorResponse {
    //Response message
    private String message;

    private int errorCode;

    public ErrorResponse(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        switch (getErrorCode()) {
            case Constants.NOT_PRODUCTS_FOUND_CODE:
                return Constants.NOT_PRODUCTS_FOUND;
            case Constants.INCORRECT_DATE_FORMAT_CODE:
                return Constants.INCORRECT_DATE_FORMAT;
            case Constants.ERROR_GETTING_PRICES_CODE:
                return Constants.ERROR_GETTING_PRICES;
            case Constants.MISSING_PARAM_FIELDS_CODE:
                return Constants.MISSING_PARAM_FIELDS;
            case Constants.INCORRECT_FORMAT_FIELDS_CODE:
                return Constants.INCORRECT_FORMAT_FIELDS;
            case Constants.INCORRECT_CARD_CODE:
                return Constants.INCORRECT_CARD_MSG;
            case Constants.INSUFFICIENT_PRODUCT_STOCK:
                return Constants.INSUFFICIENT_PRODUCT_MSG;
            default:
                setErrorCode(Constants.UNKNOWN_ERRROR_CODE);
                return Constants.UNKNOWN_ERROR;
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
