package com.content.POJO.DOProject;

import lombok.Data;

import java.util.Date;

@Data
public class TableUserInformationDO {
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
