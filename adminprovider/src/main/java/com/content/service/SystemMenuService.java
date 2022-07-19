package com.content.service;

import com.content.POJO.dto.SystemMenuTableDTO;
import com.content.POJO.vo.SystemMenuReponseVO;

/**
 * {说明}系统菜单服务
 *
 * @author littledotboy
 * @date 2022/07/19
 */
public interface SystemMenuService {
    /**
     * {方法注释}得到所有menulist
     *
     * @return {@link SystemMenuReponseVO }
     * @author littledotboy
     * @date 2022/07/19
     */
    public SystemMenuReponseVO getAllMenulist();

    /**
     * {方法注释}插入单个系统菜单
     *
     * @param systemMenuTableDTO 系统菜单表dto
     * @author littledotboy
     * @date 2022/07/19
     */
    public void insertSingleSystemMenu(SystemMenuTableDTO systemMenuTableDTO);

    /**
     * {方法注释}删除单个系统菜单
     *
     * @param systemMenuTableDTO 系统菜单表dto
     * @author littledotboy
     * @date 2022/07/19
     */
    public void deleteSingleSystemMenu(SystemMenuTableDTO systemMenuTableDTO);

    /**
     * {方法注释}更新单一系统菜单
     *
     * @param systemMenuTableDTO 系统菜单表dto
     * @author littledotboy
     * @date 2022/07/19
     */
    public void updateSingleSystemMenu(SystemMenuTableDTO systemMenuTableDTO);
}
