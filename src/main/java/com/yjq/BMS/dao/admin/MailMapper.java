package com.yjq.BMS.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yjq.BMS.pojo.admin.Mail;
/**
 * 邮箱mapper接口
 * @author 82320
 *
 */
public interface MailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mail record);

    int insertSelective(Mail record);

    Mail selectByPrimaryKey(Integer id);
    
    List<Mail> selectByReceiverIdAndDeleteState(@Param("id") Integer id, @Param("deleteState") Integer deleteState);
    
    List<Mail> selectBySenderIdAndDeleteState(@Param("id") Integer id, @Param("deleteState") Integer deleteState);
    
    List<Mail> selectBySearchTitleAndReceiverIdAndDeleteState(@Param("title") String title , @Param("id") Integer id, @Param("deleteState") Integer deleteState);
    
    List<Mail> selectBySearchTitleAndSenderIdAndDeleteState(@Param("title") String title , @Param("id") Integer id, @Param("deleteState") Integer deleteState);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKeyWithBLOBs(Mail record);

    int updateByPrimaryKey(Mail record);
    
    //一次插入多条数据
    int batchInsert(@Param("mailList") List<Mail> mailList);
    
    int getTotal();
}