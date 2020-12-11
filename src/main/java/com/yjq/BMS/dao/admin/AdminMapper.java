package com.yjq.BMS.dao.admin;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yjq.BMS.pojo.admin.Admin;
/**
 * 管理员mapper接口
 * @author 82320
 *
 */
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);
    
    Admin selectByNameAndPassword(@Param("name") String name, @Param("password") String password);
    
    List<Admin> selectByRoleId(Integer id);

    int updateByPrimaryKeySelective(Admin record);
    
    int updateByPrimaryKeySetSelective(@Param("adminIdSet") Set<Integer> adminIdSet,@Param("admin") Admin admin);

    int updateByPrimaryKey(Admin record);
    
    List<Admin> selectAll();
    
    List<Admin> selectBySearchName(String name);
    
    Admin selectByName(String name);
    
    int getTotal();
    
}