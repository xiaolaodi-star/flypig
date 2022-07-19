package com.content.POJO.vo;

import lombok.Data;

@Data
public class SystemMenuRequestVO {
    private int id;
    private String icon;
    private String name;
    private String view;
    private int parentId;
    private String type;
    private String states;
}
