package com.common.errorcode;

public class PersonalRunException extends RuntimeException {
    protected ErrorCode errorCode;
    public PersonalRunException(){}
    public PersonalRunException(ErrorCode e){
        super();
        this.errorCode=e;
    }
    public PersonalRunException(ErrorCode code,Throwable e){
        super(e);
        this.errorCode=code;
    }

    public ErrorCode getCode() {
        return errorCode;
    }
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
