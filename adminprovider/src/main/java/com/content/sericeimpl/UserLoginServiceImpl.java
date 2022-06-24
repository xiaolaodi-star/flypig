package com.content.sericeimpl;

import com.content.POJO.DOProject.TableUserInformationDO;
import com.content.POJO.query.UserLoginQuery;
import com.content.POJO.vo.UserLoginRequestVO;
import com.content.mapper.TableUserInformationMapper;
import com.content.service.UserLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {说明}用户登录服务impl
 *
 * @author littledotboy
 * @date 2022/06/24
 * @see UserLoginService
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private TableUserInformationMapper tableUserInformationMapper;
    @Override
    public boolean userLoginStatus(UserLoginRequestVO requestVO) {
        UserLoginQuery userLoginQuery=new UserLoginQuery();
        userLoginQuery.setName(requestVO.getName());
        userLoginQuery.setPassword(requestVO.getPassword());
        List<TableUserInformationDO> tableUserInformationDOList=tableUserInformationMapper.getUserPassord(userLoginQuery);
        if(tableUserInformationDOList.size()==0){
            return false;
        }else {
            return true;
        }
    }
}
