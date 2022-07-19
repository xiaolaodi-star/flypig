package com.content.controller;

import com.common.errorcode.ErrorCode;
import com.common.errorcode.PersonalRunException;
import com.content.POJO.dto.SystemMenuTableDTO;
import com.content.POJO.vo.SystemMenuReponseVO;
import com.content.POJO.vo.SystemMenuRequestVO;
import com.content.service.SystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {说明}系统菜单控制器
 *
 * @author littledotboy
 * @date 2022/07/19
 */
@RestController
@Slf4j
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;
    @RequestMapping("/system/menulist")
    public SystemMenuReponseVO getSystemMenu(){
        log.info("系统菜单列表请求中---------》");
        SystemMenuReponseVO reponseVO=systemMenuService.getAllMenulist();
        log.info("系统菜单列表请求完成：{}",reponseVO.getMenuTableDTOMap());
        return reponseVO;
    }

    /**
     * {方法注释}插入菜单
     *
     * @param requestVO 请求签证官
     * @author littledotboy
     * @date 2022/07/19
     */
    @RequestMapping("/system/menulist/insertmenu")
    public void insertMenu(@RequestBody SystemMenuRequestVO requestVO){
        SystemMenuTableDTO dto=new SystemMenuTableDTO();
        if(requestVO!=null){
            dto.setId(requestVO.getId());
            dto.setIcon(requestVO.getIcon());
            dto.setName(requestVO.getName());
            dto.setStates(requestVO.getStates());
            dto.setParentId(requestVO.getParentId());
            dto.setType(requestVO.getType());
            dto.setView(requestVO.getView());
            systemMenuService.insertSingleSystemMenu(dto);
        }else {
            log.info("缺少传入的参数");
            throw new PersonalRunException(ErrorCode.NULL_POINT);
        }
    }

    /**
     * {方法注释}更新菜单
     *
     * @param requestVO 请求签证官
     * @author littledotboy
     * @date 2022/07/19
     */
    @RequestMapping("/system/menulist/updatemenu")
    public void updateMenu(@RequestBody SystemMenuRequestVO requestVO){
        SystemMenuTableDTO dto=new SystemMenuTableDTO();
        if(requestVO!=null){
            dto.setId(requestVO.getId());
            dto.setIcon(requestVO.getIcon());
            dto.setName(requestVO.getName());
            dto.setStates(requestVO.getStates());
            dto.setParentId(requestVO.getParentId());
            dto.setType(requestVO.getType());
            dto.setView(requestVO.getView());
            systemMenuService.updateSingleSystemMenu(dto);
        }else {
            log.info("缺少传入的参数");
            throw new PersonalRunException(ErrorCode.NULL_POINT);
        }
    }

    /**
     * {方法注释}删除菜单
     *
     * @param requestVO 请求签证官
     * @author littledotboy
     * @date 2022/07/19
     */
    @RequestMapping("/system/menulist/deletemenu")
    public void deleteMenu(@RequestBody SystemMenuRequestVO requestVO){
        SystemMenuTableDTO dto=new SystemMenuTableDTO();
        if(requestVO!=null){
            dto.setId(requestVO.getId());
            dto.setIcon(requestVO.getIcon());
            dto.setName(requestVO.getName());
            dto.setStates(requestVO.getStates());
            dto.setParentId(requestVO.getParentId());
            dto.setType(requestVO.getType());
            dto.setView(requestVO.getView());
            systemMenuService.deleteSingleSystemMenu(dto);
        }else {
            log.info("缺少传入的参数");
            throw new PersonalRunException(ErrorCode.NULL_POINT);
        }
    }


}
