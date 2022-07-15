package com.content.controller;

import com.common.errorcode.*;
import com.content.POJO.vo.UserLoginRequestVO;
import com.content.service.UserLoginService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserLoginService userLoginService;
    @ResultBody
    @RequestMapping("/login")
    @ResponseBody
    public String index(UserLoginRequestVO requestVO, HttpServletRequest request){
        log.info("用户登录:{}",requestVO.toString());
        if(StringUtils.isNullOrEmpty(requestVO.getName())||
                StringUtils.isNullOrEmpty(requestVO.getPassword())){
            throw new PersonalRunException(ErrorCode.PARAM_IS_INVALID);
        }
        boolean result=userLoginService.userLoginStatus(requestVO);
        if(result==true){
            request.getSession().setAttribute("username",requestVO.getName());
            request.getSession().setAttribute("password",requestVO.getPassword());
            log.info("用户{}登录成功。",requestVO.getName());
            return "/system/dashboard/webdashboard";
        }else {
            log.info("用户账户或密码错误");
            throw new PersonalRunException(ErrorCode.FALSE);
        }
    }
}
