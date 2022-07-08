package com.common.errorcode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author littledotboy
 * @date 2022/06/10
 */
@Slf4j
@ControllerAdvice
@Data
public class GlobalException{
    /**
     * {方法注释}自定义异常处理程序
     *
     * @param req 要求事情
     * @param e   e
     * @return {@link ResultBuild }
     * @author littledotboy
     * @date 2022/07/08
     */
    @ExceptionHandler(value = PersonalRunException.class)
    @ResponseBody
    public ResultBuild exceptionHandler(HttpServletRequest req, PersonalRunException e){
        log.info("Result Error:{}",e.getMessage());
        return ResultBuild.fail(e.getCode());
    }

    /**
     * {方法注释}空指针异常
     *
     * @param req 要求事情
     * @param e   e
     * @return {@link ResultBuild }
     * @author littledotboy
     * @date 2022/07/08
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBuild exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("NullPointerException:", e);
        ResultBuild resultBuild=ResultBuild.fail(ErrorCode.NULL_POINT);
        return resultBuild;
    }

    /**
     * 访问请求异常
     *
     * @param req 要求事情
     * @param e   e
     * @return {@link ResultBuild }
     * @author littledotboy
     * @date 2022/07/08
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultBuild exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException:", e);
        return ResultBuild.fail(ErrorCode.NOT_SUPPORT);
    }

    /**
     * 其他异常
     *
     * @param req 要求事情
     * @param e   e
     * @return {@link ResultBuild }
     * @author littledotboy
     * @date 2022/07/08
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBuild exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("Exception:", e);
        return ResultBuild.fail(ErrorCode.FALSE,e.getMessage());
    }

    /**
     * 算数异常
     *
     * @param req 要求事情
     * @param e   e
     * @return {@link ResultBuild }
     * @author littledotboy
     * @date 2022/07/08
     */
    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseBody
    public ResultBuild exceptionHandler(HttpServletRequest req, ArithmeticException e) {
        log.error("ArithmeticException:", e);
        return ResultBuild.fail(ErrorCode.E_ARITHMETIC);
    }
}
