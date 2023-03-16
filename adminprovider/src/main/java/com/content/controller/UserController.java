package com.content.controller;

import com.common.errorcode.*;
import com.common.filter.AuthorizationService;
import com.common.redis.RedisUtil;
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

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserLoginService userLoginService;
    @Resource
    private AuthorizationService authorizationService;
    @Resource
    private RedisUtil redisUtil;

    @ResultBody
    @RequestMapping("/login")
    @ResponseBody
    public String index(UserLoginRequestVO requestVO, HttpServletRequest request, HttpServletResponse response){
        log.info("用户登录:{}",requestVO.toString());
        if(StringUtils.isNullOrEmpty(requestVO.getName())||
                StringUtils.isNullOrEmpty(requestVO.getPassword())){
            throw new PersonalRunException(ErrorCode.PARAM_IS_INVALID);
        }
        boolean result=userLoginService.userLoginStatus(requestVO);
        if(result==true){
            String username=requestVO.getName();
            String accessToken=authorizationService.createAccessIdToken(username);
            String refreshToken=authorizationService.createRefreshIdToken(username);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            redisUtil.set("accesstoken_"+username,accessToken,(int)AuthorizationService.accessTokenExpirationTime);
            redisUtil.set("refreshtoken_"+username,refreshToken,(int)AuthorizationService.refreshTokenExpirationTime);
            Cookie cookie=new Cookie("accessToken",accessToken);
            Cookie refreshcookie=new Cookie("refreshtoken",refreshToken);
            response.addCookie(cookie);
            response.addCookie(refreshcookie);
            log.info("用户{}登录成功。",requestVO.getName());
            return "/system/dashboard/webdashboard";
        }else {
            log.info("用户账户或密码错误");
            throw new PersonalRunException(ErrorCode.FALSE);
        }
    }
}
