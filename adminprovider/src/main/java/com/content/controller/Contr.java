package com.content.controller;

import com.common.errorcode.ResultBody;
import com.content.POJO.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class Contr{
    @ResultBody
    @RequestMapping("/test")
    public int result(){
        return 1;
    }
    @ResultBody
    @RequestMapping("/index")
    public int index(HttpSession session){
        log.info("用户登录");
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1);
        userDTO.setName("a");
        session.setAttribute("user",userDTO);
        return 1;
    }
}
