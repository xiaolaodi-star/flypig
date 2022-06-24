package com.content.POJO.dto;

import lombok.Data;

import java.util.Date;
@Data
public class TableUserInformationDTO {
    private int id;
    private String name;
    private String sex;
    private Date userBirthday;
    private Date createTime;
    private Date updateTime;
    private Date lastLoginTime;
    private String loginStatus;
    private String userLevel;
    private String emailAddress;
    private String description;
    private String password;
}
