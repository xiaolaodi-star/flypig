package com.content.POJO.dto;

import lombok.Data;

@Data
public class SystemMenuTableDTO {
    private int id;
    private String icon;
    private String name;
    private String view;
    private String parentId;
    private String type;
    private String states;
}
