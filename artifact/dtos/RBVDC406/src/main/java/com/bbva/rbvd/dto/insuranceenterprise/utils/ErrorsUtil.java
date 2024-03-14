package com.bbva.rbvd.dto.insuranceenterprise.utils;

public enum ErrorsUtil {

    QUOTATIONS_EMPTY_BY_CUSTOMER("RBVD01020091","No se encontró ninguna cotización del cliente para el producto Vida Ley");


    private final String errorCode;
    private final String errorMessage;


    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    ErrorsUtil(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
