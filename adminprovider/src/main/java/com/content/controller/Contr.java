package com.content.controller;

import com.common.errorcode.ErrorCode;
import com.common.errorcode.ResultBody;
import com.common.errorcode.ResultBuild;
import com.content.POJO.vo.UserLoginRequestVO;
import com.content.service.UserLoginService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class Contr{
    @Autowired
    private UserLoginService userLoginService;
    @ResultBody
    @RequestMapping("/login")
    public ResultBuild index(@RequestBody UserLoginRequestVO requestVO, HttpServletRequest request){
        log.info("用户登录");
        if(requestVO!=null||
                StringUtils.isNullOrEmpty(requestVO.getName())||
                StringUtils.isNullOrEmpty(requestVO.getPassword())){
            return ResultBuild.fail( ErrorCode.PARAM_IS_INVALID,requestVO);
        }
        boolean result=userLoginService.userLoginStatus(requestVO);
        if(result==true){
            request.getSession().setAttribute("username",requestVO.getName());
            request.getSession().setAttribute("password",requestVO.getPassword());
            log.info("用户{}登录成功。",requestVO.getName());
            return ResultBuild.success("success");
        }else {
            return ResultBuild.fail(ErrorCode.SUCCESS,"用户账户或密码错误");
        }
    }
}
