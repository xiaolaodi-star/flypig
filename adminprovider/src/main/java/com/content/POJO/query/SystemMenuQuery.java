package com.content.POJO.query;

import lombok.Data;

@Data
public class SystemMenuQuery {
    private int id;
    private String icon;
    private String name;
    private String view;
    private int parentId;
    private String type;
    private String states;
}
