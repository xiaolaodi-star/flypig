package com.common.errorcode;

/**
 * {说明}错误代码
 *
 * @author littledotboy
 * @date 2022/06/09
 * @see Enum
 */
public enum ErrorCode {
    SUCCESS(0000,"处理成功"),
    PARAM_IS_INVALID(1001,"缺少必需参数"),
    FALSE(9999,"处理失败");
    private Integer code;
    private String message;
    ErrorCode(Integer integer,String message){
        this.code=integer;
        this.message=message;
    }
    public Integer getCode(){return code;}
    public String getMessage() {return message;}
}
