package com.yjq.BMS.dao.admin;

import java.util.List;

import com.yjq.BMS.pojo.admin.Announcement;
/**
 * 公告mapper接口
 * @author 82320
 *
 */
public interface AnnouncementMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByAdminId(Integer adminId);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);
    
    List<Announcement> selectAll();
    
    List<Announcement> selectBySearchContent(String content);
    
    int getTotal();
}