package com.yjq.BMS.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjq.BMS.pojo.admin.Authority;
/**
 * 权限mapper接口
 * @author 82320
 *
 */
public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByRoleId(Integer id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer id);
    
    List<Authority> selectByRoleId(Integer id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
    
    //一次插入多条数据
    int batchInsert(@Param("authorityList") List<Authority> authorityList);
}