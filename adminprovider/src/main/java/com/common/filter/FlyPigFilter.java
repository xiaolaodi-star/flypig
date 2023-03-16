package com.common.filter;

import com.content.POJO.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Order(1)
@Slf4j
@WebFilter(filterName = "myfilter",urlPatterns={"/*"})
public class FlyPigFilter implements Filter {
    private List<UserDTO> userDTOList=new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<String> passUrl=new ArrayList<String>();
        passUrl.add("/Index");
        passUrl.add("/favicon.ico");
        passUrl.add("/css/");
        passUrl.add("/js/");
        passUrl.add("/image/");
        passUrl.add("/login");
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(httpResponse);
//        采用双token机制
        Cookie[] cookies=request.getCookies();

        String requestURL= request.getRequestURI();
        log.info("请求路径：{}",requestURL);
//        boolean pass=false;
//        for (String uri:passUrl) {
//            if (requestURL.contains(uri)){pass=true;}
//        }
//        pass=true;
//        if(pass){
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//        else {
//            Object username=request.getSession().getAttribute("username");
//            Object password=request.getSession().getAttribute("password");
//            if(username!=null||
//            password!=null){
//                UserDTO userDTO=new UserDTO();
//                userDTO.setName(username.toString());
//                userDTO.setPassword(password.toString());
//                userDTOList.add(userDTO);
//                filterChain.doFilter(servletRequest,servletResponse);
//            }else {
//                log.info("用户未进行登录");
//                wrapper.sendRedirect("/Index");
//            }
//        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {
        log.info("过滤器关闭");
        Filter.super.destroy();
    }
}
