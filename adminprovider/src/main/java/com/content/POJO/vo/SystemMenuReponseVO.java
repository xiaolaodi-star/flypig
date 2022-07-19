package com.content.POJO.vo;

import com.content.POJO.dto.SystemMenuTableDTO;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * {说明}系统菜单响应签证官
 *
 * @author littledotboy
 * @date 2022/07/19
 */
@Data
public class SystemMenuReponseVO {
    /**
     * {属性注释}菜单表dtomap
     *
     * @author littledotboy
     * @date 2022/07/19
     */
//    图标
    Map <String, String> menuTableDTOMap = new HashMap<>();
//    子菜单
    Map<String,String> secondMenu=new HashMap<>();
//    二级菜单图标
    Map<String,String> secondicon=new HashMap<>();
//    二级菜单跳转链接
    Map<String,String> secondlink=new HashMap<>();

}
