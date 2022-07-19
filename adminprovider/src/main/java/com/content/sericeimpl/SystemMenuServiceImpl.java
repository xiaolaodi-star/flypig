package com.content.sericeimpl;

import com.content.POJO.DOProject.SystemMenuTableDO;
import com.content.POJO.dto.SystemMenuTableDTO;
import com.content.POJO.query.SystemMenuQuery;
import com.content.POJO.vo.SystemMenuReponseVO;
import com.content.mapper.SystemMenuTable;
import com.content.service.SystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {说明}系统菜单服务impl
 *
 * @author littledotboy
 * @date 2022/07/19
 */
@Service
@Slf4j
public class SystemMenuServiceImpl implements SystemMenuService {
    @Autowired
    private SystemMenuTable systemMenuTable;

    /**
     * {方法注释}得到所有menulist
     *
     * @return {@link SystemMenuReponseVO }
     * @author littledotboy
     * @date 2022/07/19
     */
    @Override
    public SystemMenuReponseVO getAllMenulist() {
        SystemMenuReponseVO reponseVO=new SystemMenuReponseVO();
        SystemMenuQuery query=new SystemMenuQuery();
        Map<String,String> onemenu=new HashMap<>();
        Map<String,String> twomenu=new HashMap<>();
        Map<String,String> twoicon=new HashMap<>();
        Map<String,String> twolink=new HashMap<>();
        query.setView("yes");
        query.setParentId(0);
        List<SystemMenuTableDO> list=systemMenuTable.selectAll(query);
        log.info("size:{}",list.size());
        for (SystemMenuTableDO systemMenuTableDO:list) {
            onemenu.put(systemMenuTableDO.getName(),systemMenuTableDO.getIcon());
            query.setParentId(systemMenuTableDO.getId());
            List<SystemMenuTableDO> list1=systemMenuTable.selectAll(query);
            String secondmenu="";
            for (SystemMenuTableDO tableDO:list1) {
                if(secondmenu.length()>0){
                    secondmenu=secondmenu+"|"+tableDO.getName();
                }else {
                    secondmenu= tableDO.getName();
                }
                twoicon.put(tableDO.getName(), tableDO.getIcon());
                twolink.put(tableDO.getName(), tableDO.getType());
            }
            twomenu.put(systemMenuTableDO.getName(),secondmenu);
        }
        reponseVO.setMenuTableDTOMap(onemenu);
        reponseVO.setSecondMenu(twomenu);
        reponseVO.setSecondicon(twoicon);
        reponseVO.setSecondlink(twolink);
//        本部分未对是否对外部展示进行过滤
        return reponseVO;
    }

    /**
     * {方法注释}插入单个系统菜单
     *
     * @param systemMenuTableDTO 系统菜单表dto
     * @author littledotboy
     * @date 2022/07/19
     */
    @Override
    public void insertSingleSystemMenu(SystemMenuTableDTO systemMenuTableDTO) {
        SystemMenuQuery query=new SystemMenuQuery();
        if(systemMenuTableDTO!=null){
            query.setId(systemMenuTableDTO.getId());
            query.setIcon(systemMenuTableDTO.getIcon());
            query.setStates(systemMenuTableDTO.getStates());
            query.setName(systemMenuTableDTO.getName());
            query.setType(systemMenuTableDTO.getType());
            query.setParentId(systemMenuTableDTO.getParentId());
            query.setView(systemMenuTableDTO.getView());
            systemMenuTable.insertSingleSystemMenu(query);
            log.info("插入数据成功：{}",query.toString());
        }else {
            log.info("插入数据为空.");
        }
    }

    /**
     * {方法注释}删除单个系统菜单
     *
     * @param systemMenuTableDTO 系统菜单表dto
     * @author littledotboy
     * @date 2022/07/19
     */
    @Override
    public void deleteSingleSystemMenu(SystemMenuTableDTO systemMenuTableDTO) {
        SystemMenuQuery query=new SystemMenuQuery();
        if(systemMenuTableDTO!=null){
            query.setId(systemMenuTableDTO.getId());
            query.setIcon(systemMenuTableDTO.getIcon());
            query.setStates(systemMenuTableDTO.getStates());
            query.setName(systemMenuTableDTO.getName());
            query.setType(systemMenuTableDTO.getType());
            query.setParentId(systemMenuTableDTO.getParentId());
            query.setView(systemMenuTableDTO.getView());
            systemMenuTable.deleteSingleSystemMenu(query);
            log.info("删除数据成功：{}",query.toString());
        }else {
            log.info("删除数据为空.");
        }
    }

    /**
     * {方法注释}更新单一系统菜单
     *
     * @param systemMenuTableDTO 系统菜单表dto
     * @author littledotboy
     * @date 2022/07/19
     */
    @Override
    public void updateSingleSystemMenu(SystemMenuTableDTO systemMenuTableDTO) {
        SystemMenuQuery query=new SystemMenuQuery();
        if(systemMenuTableDTO!=null){
            query.setId(systemMenuTableDTO.getId());
            query.setIcon(systemMenuTableDTO.getIcon());
            query.setStates(systemMenuTableDTO.getStates());
            query.setName(systemMenuTableDTO.getName());
            query.setType(systemMenuTableDTO.getType());
            query.setParentId(systemMenuTableDTO.getParentId());
            query.setView(systemMenuTableDTO.getView());
            systemMenuTable.updateSingleSystemMenu(query);
            log.info("更新数据成功：{}",query.toString());
        }else {
            log.info("更新数据为空.");
        }
    }
}
