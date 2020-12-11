package com.yjq.BMS.dao.admin;

import java.util.List;

import com.yjq.BMS.pojo.admin.Attachment;
/**
 * 附件mapper接口
 * @author 82320
 *
 */
public interface AttachmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Integer id);
    
    List<Attachment> selectAll();

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);
    
    int getTotal();
}