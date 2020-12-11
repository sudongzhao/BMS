package com.yjq.BMS.dao.admin;

import java.util.List;

import com.yjq.BMS.pojo.admin.Role;
/**
 * 角色mapper接口
 * @author 82320
 *
 */
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);
    
    Role selectByName(String name);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectAll();
    
    List<Role> selectBySearchName(String name);
    
    int getTotal();
}