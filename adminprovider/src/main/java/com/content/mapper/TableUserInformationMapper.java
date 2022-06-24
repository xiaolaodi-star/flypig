package com.content.mapper;

import com.content.POJO.DOProject.TableUserInformationDO;
import com.content.POJO.query.UserLoginQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableUserInformationMapper {
    /**
     * {方法注释}获取用户passord
     *
     * @param query 查询
     * @return {@link List }<{@link TableUserInformationDO }>
     * @author littledotboy
     * @date 2022/06/24
     */
    List<TableUserInformationDO> getUserPassord(UserLoginQuery query);

    /**
     * {方法注释}选择用户
     *
     * @param tableUserInformationDO 用户信息表
     * @return {@link List }<{@link TableUserInformationDO }>
     * @author littledotboy
     * @date 2022/06/24
     */
    List<TableUserInformationDO> selectUser(TableUserInformationDO tableUserInformationDO);

    /**
     * {方法注释}插入结果
     *
     * @param tableUserInformationDO 用户信息表
     * @return {@link Integer }
     * @author littledotboy
     * @date 2022/06/24
     */
    Integer insertResult(TableUserInformationDO tableUserInformationDO);

    /**
     * {方法注释}更新结果
     *
     * @param tableUserInformationDO 用户信息表
     * @return {@link Integer }
     * @author littledotboy
     * @date 2022/06/24
     */
    Integer updateResult(TableUserInformationDO tableUserInformationDO);

    /**
     * {方法注释}删除结果
     *
     * @param tableUserInformationDO 用户信息表
     * @return {@link Integer }
     * @author littledotboy
     * @date 2022/06/24
     */
    Integer deleteResult(TableUserInformationDO tableUserInformationDO);
}
