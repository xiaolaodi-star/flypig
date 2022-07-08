package com.common.errorcode;

import lombok.Data;

import java.io.Serializable;

/**
 * 结果构建{返回体封装类}
 *
 * @author littledotboy
 * @date 2022/06/09
 */
@Data
public class ResultBuild implements Serializable {
    private Integer code;
    private String message;
    private Object data;
   public static ResultBuild success(){
       ResultBuild resultBuild=new ResultBuild();
       resultBuild.code=ErrorCode.SUCCESS.getCode();
       resultBuild.message=ErrorCode.SUCCESS.getMessage();
       return resultBuild;
   }
   public static ResultBuild success(ErrorCode errorCode){
       ResultBuild resultBuild=new ResultBuild();
       resultBuild.code= errorCode.getCode();
       resultBuild.message=errorCode.getMessage();
       return resultBuild;
   }
   public static ResultBuild success(ErrorCode errorCode,Object data){
       ResultBuild resultBuild=new ResultBuild();
       resultBuild.code=errorCode.getCode();
       resultBuild.message=errorCode.getMessage();
       resultBuild.data=data;
       return resultBuild;
   }
   public static ResultBuild success(Object data){
       ResultBuild resultBuild=new ResultBuild();
       resultBuild.code=ErrorCode.SUCCESS.getCode();
       resultBuild.message=ErrorCode.SUCCESS.getMessage();
       resultBuild.data=data;
       return resultBuild;
   }
   public static ResultBuild fail(ErrorCode errorCode){
       ResultBuild resultBuild=new ResultBuild();
       resultBuild.code= errorCode.getCode();
       resultBuild.message= errorCode.getMessage();
       return resultBuild;
   }
   public static ResultBuild fail(ErrorCode errorCode,Object data){
       ResultBuild resultBuild=new ResultBuild();
       resultBuild.code= errorCode.getCode();
       resultBuild.message= errorCode.getMessage();
       resultBuild.data=data;
       return resultBuild;
   }

}
