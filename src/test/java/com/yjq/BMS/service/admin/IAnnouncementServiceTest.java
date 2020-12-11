package com.yjq.BMS.service.admin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yjq.BMS.BMSApplicationTest;
import com.yjq.BMS.bean.CodeMsg;
import com.yjq.BMS.pojo.admin.Announcement;
import com.yjq.BMS.vo.common.ResponseVo;

import lombok.extern.slf4j.Slf4j;
/**
 * 公告service测试类
 * @author 82320
 *
 */
@Slf4j
//有@After的delete方法，可以不用下面的@Transactional事务回滚
//@Transactional   //在测试类中的事务回滚（即使数据都不报错也会回滚，不在数据库中留下测试数据）
public class IAnnouncementServiceTest extends BMSApplicationTest{

	@Autowired
	private IAnnouncementService announcementService;
	
	//把返回数据json格式化
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private Integer loginedId = 1;
	
	private Announcement announcement;
	
	@Before //初始化方法   对于每一个测试方法执行前都要执行一次
	public void before() {
		log.info("添加公告...");
		Announcement announcement = new Announcement(null,"测试公告",loginedId);
		this.announcement = announcement;
		add();
	}
	
	@After //释放资源  对于每一个测试方法执行后都要执行一次
	public void delete() {
		ResponseVo<Boolean> delete = announcementService.delete(announcement.getId());
		log.info("result={}",gson.toJson(delete));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), delete.getCode());
	}

	
	/**
	 * 测试发布公告
	 */
	public void add() {
		ResponseVo<Boolean> add = announcementService.add(announcement, loginedId);
		//id = menu.getId();//useGeneratedKeys="true" keyProperty="id" 自动返回添加数据的id
		log.info("result={}",gson.toJson(add));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), add.getCode());
	}
	
	/**
	 * 测试根据分页获取公告数据
	 */
	@Test
	public void getAnnouncementByPage() {
		ResponseVo<PageInfo> announcementByPage = announcementService.getAnnouncementByPage(1, 1);
		log.info("result={}",gson.toJson(announcementByPage));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), announcementByPage.getCode());
	}
	
	/**
	 * 测试根据分页和查询信息获取公告数据
	 */
	@Test
	public void getAnnouncementByPageAndContent() {
		ResponseVo<PageInfo> announcementByPageAndContent = announcementService.getAnnouncementByPageAndContent(1, 1, "测试");
		log.info("result={}",gson.toJson(announcementByPageAndContent));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), announcementByPageAndContent.getCode());
	}
	
}
