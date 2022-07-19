-- auto-generated definition
create table admin_user
(
    id              int auto_increment
        primary key,
    user_name       varchar(255) null,
    user_password   varchar(255) null,
    emailaddress    varchar(255) null,
    telephone       varchar(255) null,
    create_time     datetime     null,
    last_login_time datetime     null,
    description     text         null
);

-- auto-generated definition
create table personal_information
(
    id             int          not null
        primary key,
    user_id        int          not null,
    user_name      varchar(255) null,
    user_telephone varchar(255) null,
    user_email     varchar(255) null,
    user_level     varchar(255) null,
    createtime     datetime     null,
    infomation     text         null
);

-- auto-generated definition
create table system_menu_table
(
    id        int          not null
        primary key,
    icon      varchar(255) null comment '图标',
    name      varchar(255) null comment '中文简介',
    view      varchar(255) null comment '可见：yes:no',
    parent_id int          null comment '父节点id',
    type      varchar(255) null comment '菜单类型menu：api',
    states    varchar(255) null comment '是否外部展示'
);

-- auto-generated definition
create table table_user_information
(
    id              int auto_increment comment 'id'
        primary key,
    name            varchar(255) not null comment '用户姓名，不可为空',
    sex             varchar(255) null comment '用户性别，可以为空',
    user_birthday   timestamp    null comment '用户出生年月，可以为空',
    create_time     timestamp    not null comment '创建时间，不为空',
    update_time     timestamp    not null comment '更新时间，不为空',
    last_login_time timestamp    not null comment '上一次登录时间，不为空',
    login_status    varchar(255) not null comment '登录状态，“login”、“exit”、“destroy”',
    user_level      int          not null comment '用户等级，1-5，一级最高'
);

-- auto-generated definition
create table user_login_info
(
    id         int          not null
        primary key,
    name       varchar(255) null,
    password   varchar(255) not null,
    status     varchar(255) null,
    createtime datetime     null,
    updatetime datetime     null
);

-- auto-generated definition
create table words
(
    id            int auto_increment
        primary key,
    typeofcontent varchar(255) null,
    content       text         null,
    txtfilepath   text         null,
    createtime    datetime     null,
    updatetime    datetime     null,
    builder       varchar(255) null,
    author        varchar(255) null
);

CREATE TABLE `system_menu_table` (
                                     `id` int NOT NULL,
                                     `icon` varchar(255) DEFAULT NULL COMMENT '图标',
                                     `name` varchar(255) DEFAULT NULL COMMENT '中文简介',
                                     `view` varchar(255) DEFAULT NULL COMMENT '可见：yes:no',
                                     `parent_id` int DEFAULT NULL COMMENT '父节点id',
                                     `type` varchar(255) DEFAULT NULL COMMENT '菜单类型menu：api',
                                     `states` varchar(255) DEFAULT NULL COMMENT '是否外部展示',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;