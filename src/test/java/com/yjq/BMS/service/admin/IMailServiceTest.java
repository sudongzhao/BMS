package com.yjq.BMS.service.admin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yjq.BMS.BMSApplicationTest;
import com.yjq.BMS.bean.CodeMsg;
import com.yjq.BMS.pojo.admin.Mail;
import com.yjq.BMS.vo.common.ResponseVo;

import lombok.extern.slf4j.Slf4j;
/**
 * 邮件service测试类
 * @author 82320
 *
 */
@Slf4j
//有@After的delete方法，可以不用下面的@Transactional事务回滚
@Transactional   //在测试类中的事务回滚（即使数据都不报错也会回滚，不在数据库中留下测试数据）
public class IMailServiceTest extends BMSApplicationTest{

	@Autowired
	private IMailService mailService;
	
	//把返回数据json格式化
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private Mail mail;
	
	private Integer loginedId = 1;
	
	private Integer senderId = 1;
	
	private Integer receiverId = 2;
	
	@Before //初始化方法   对于每一个测试方法执行前都要执行一次
	public void before() {
		log.info("添加邮件...");
		Mail mail = new Mail(null,senderId,null,"测试邮件",null,null,null,3,"<p>测试邮件正文</p>");
		this.mail = mail;
		sendMail();
	}
	
	/**
	 * 测试邮件发送
	 */
	public void sendMail() {
		ResponseVo<Boolean> sendMail = mailService.sendMail(mail, receiverId.toString(), loginedId);
		//id = menu.getId();//useGeneratedKeys="true" keyProperty="id" 自动返回添加数据的id
		log.info("result={}",gson.toJson(sendMail));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), sendMail.getCode());
	}
	
	/**
	 * 根据搜索条件和分页查找收件箱邮件
	 */
	@Test
	public void getReceiveMailsByPageAndTitle() {
		ResponseVo<PageInfo> receiveMailsByPageAndTitle = mailService.getReceiveMailsByPageAndTitle(1, 1, receiverId, "开发人员");
		log.info("result={}",gson.toJson(receiveMailsByPageAndTitle));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), receiveMailsByPageAndTitle.getCode());
	}
	
	/**
	 * 根据搜索条件和分页查找发件箱邮件
	 */
	@Test
	public void getSendMailsByPageAndTitle() {
		ResponseVo<PageInfo> sendMailsByPageAndTitle = mailService.getSendMailsByPageAndTitle(1, 1, senderId, "开发人员");
		log.info("result={}",gson.toJson(sendMailsByPageAndTitle));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), sendMailsByPageAndTitle.getCode());
	}
	
}
