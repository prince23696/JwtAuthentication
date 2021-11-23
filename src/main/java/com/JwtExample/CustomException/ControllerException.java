package com.JwtExample.CustomException;

public class ControllerException extends RuntimeException{

    static  long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        BusinessException.serialVersionUID = serialVersionUID;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ControllerException() {
    }

    public ControllerException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
