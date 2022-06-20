package com.common.errorcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 返回体结果封装
 *
 * @author littledotboy
 * @date 2022/06/09
 * @see ResponseBodyAdvice
 */
@Slf4j
@ControllerAdvice
public class ResponseResultBuild implements ResponseBodyAdvice<Object> {
    // 判断是否要执行 beforeBodyWrite 方法，true为执行，false不执行，有注解标记的时候处理返回值
    public final String RESPONSE_RESULT="RESPONSE_RESULT";
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        ResultBody responseResultAnn = (ResultBody) request.getAttribute(RESPONSE_RESULT);
        return responseResultAnn == null ? false : true;
    }
    // 对返回值做包装处理
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("返回体：{}",body.toString());
        if (body instanceof ResultBuild){
            return (ResultBuild)body;
        }else if (body instanceof String){
            return body;
        }
        return ResultBuild.success(body);
    }
}
