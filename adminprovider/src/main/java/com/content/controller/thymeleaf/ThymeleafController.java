package com.content.controller.thymeleaf;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
//org.springframework.web.servlet.ModelAndView
import java.util.Map;

/**
 * {说明}thymeleaf控制器
 *
 * @author littledotboy
 * @date 2022/06/13
 */
@RestController
@ResponseBody
public class ThymeleafController {
    @RequestMapping(value = {"/","/Index"})
    public ModelAndView SystemIndex(){
        ModelAndView modelAndView=new ModelAndView("/Index");
        return modelAndView;
    }
    @RequestMapping(value = "/Error")
    public ModelAndView SystemError(){
        ModelAndView modelAndView=new ModelAndView("/404page");
        return modelAndView;
    }
    @RequestMapping(value = "/favicon.ico")
    public void getIcon(){
        ModelAndView modelAndView = new ModelAndView();
//        return modelAndView;
    }
    @RequestMapping(value = "/system/dashboard/webdashboard")
    public ModelAndView getWebDashboard(){
        return new ModelAndView("/system/dashboard/webdashboard");
    }
}