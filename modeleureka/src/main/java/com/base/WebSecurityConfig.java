package com.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{
//        关闭csrf
        httpSecurity.csrf().disable();
//        支持httpbasic
        httpSecurity.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

}
