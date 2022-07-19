package com.content.mapper;

import com.content.POJO.DOProject.SystemMenuTableDO;
import com.content.POJO.query.SystemMenuQuery;
import com.content.service.SystemMenuService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * {说明}系统菜单表
 *
 * @author littledotboy
 * @date 2022/07/19
 */
@Mapper
public interface SystemMenuTable {
    /**
     * {方法注释}选择所有
     *
     * @return {@link List }<{@link SystemMenuTableDO }>
     * @author littledotboy
     * @date 2022/07/19
     */
    public List<SystemMenuTableDO> selectAll(SystemMenuQuery query);

    /**
     * {方法注释}插入单个系统菜单
     *
     * @param query 查询
     * @author littledotboy
     * @date 2022/07/19
     */
    public void insertSingleSystemMenu(SystemMenuQuery query);

    /**
     * {方法注释}更新单一系统菜单
     *
     * @param query 查询
     * @author littledotboy
     * @date 2022/07/19
     */
    public void updateSingleSystemMenu(SystemMenuQuery query);

    /**
     * {方法注释}删除单个系统菜单
     *
     * @param query 查询
     * @author littledotboy
     * @date 2022/07/19
     */
    public void deleteSingleSystemMenu(SystemMenuQuery query);

}
