package com.common.errorcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author littledotboy
 * @date 2022/06/10
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBuild exception(Exception e){
        log.info("错误：{}",e.getMessage());
        return ResultBuild.fail(ErrorCode.FALSE,"结果异常");
    }

}
