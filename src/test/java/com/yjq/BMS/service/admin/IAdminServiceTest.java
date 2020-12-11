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
import com.yjq.BMS.pojo.admin.Admin;
import com.yjq.BMS.vo.common.ResponseVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 管理员service测试类
 * @author 82320
 *
 */
@Slf4j
//有@After的delete方法，可以不用下面的@Transactional事务回滚
//@Transactional   //在测试类中的事务回滚（即使数据都不报错也会回滚，不在数据库中留下测试数据）
public class IAdminServiceTest extends BMSApplicationTest{

	@Autowired
	private IAdminService adminService;
	
	//把返回数据json格式化
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private Admin admin;
	
	@Before //初始化方法   对于每一个测试方法执行前都要执行一次
	public void before() {
		log.info("添加管理员...");
		Admin admin = new Admin(null, 1,"common/default_img.jpg","项目经理","123456",1,"福建省福州市",Long.parseLong("13882334232"),1);
		this.admin = admin;
		add();
	}
	
	@After //释放资源  对于每一个测试方法执行后都要执行一次
	public void delete() {
		ResponseVo<Boolean> delete = adminService.delete(admin.getId());
		log.info("result={}",gson.toJson(delete));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), delete.getCode());
	}
	
	
	/**
	 * 测试管理员添加
	 */
	public void add() {
		ResponseVo<Boolean> add = adminService.add(admin);
		//id = menu.getId();//useGeneratedKeys="true" keyProperty="id" 自动返回添加数据的id
		log.info("result={}",gson.toJson(add));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), add.getCode());
	}
	
	/**
	 * 测试管理员编辑
	 */
	@Test
	public void edit() {
		admin.setName("开发人员2");
		ResponseVo<Admin> edit = adminService.edit(admin);
		log.info("result={}",gson.toJson(edit));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), edit.getCode());
	}
	
	
	/**
	 * 测试根据分页获取管理员数据
	 */
	@Test
	public void getPageInfoByPage() {
		ResponseVo<PageInfo> adminListByPage = adminService.getAdminListByPage(1, 1);
		log.info("result={}",gson.toJson(adminListByPage));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), adminListByPage.getCode());
	}
	
	/**
	 * 测试根据分页和查询信息获取管理员数据
	 */
	@Test
	public void getPageInfoByPageAndName() {
		ResponseVo<PageInfo> adminListByPageAndName = adminService.getAdminListByPageAndName(1, 1, "测试人员");
		log.info("result={}",gson.toJson(adminListByPageAndName));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), adminListByPageAndName.getCode());
	}
	
	
	/**
	 * 测试管理员名称是否重名
	 */
	@Test
	public void isNameExist() {
		ResponseVo<Boolean> nameExist = adminService.isNameExist(admin, admin.getId());
		log.info("result={}",gson.toJson(nameExist));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), nameExist.getCode());
	}
}
