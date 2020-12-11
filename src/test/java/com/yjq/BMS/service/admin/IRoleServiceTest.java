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
import com.yjq.BMS.pojo.admin.Role;
import com.yjq.BMS.vo.common.ResponseVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 角色service测试类
 * @author 82320
 *
 */
@Slf4j
//有@After的delete方法，可以不用下面的@Transactional事务回滚
//@Transactional   //在测试类中的事务回滚（即使数据都不报错也会回滚，不在数据库中留下测试数据）
public class IRoleServiceTest extends BMSApplicationTest{

	@Autowired
	private IRoleService roleService;
	
	//把返回数据json格式化
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private Role role;
	
	@Before //初始化方法   对于每一个测试方法执行前都要执行一次
	public void before() {
		log.info("添加角色...");
		Role role = new Role(null,"测试管理员","测试管理员很卑微");
		this.role = role;
		add();
	}
	
	@After //释放资源  对于每一个测试方法执行后都要执行一次
	public void delete() {
		ResponseVo<Boolean> delete = roleService.delete(role.getId());
		log.info("result={}",gson.toJson(delete));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), delete.getCode());
	}

	/**
	 * 测试角色添加
	 */
	public void add() {
		ResponseVo<Boolean> add = roleService.add(role);
		//id = menu.getId();//useGeneratedKeys="true" keyProperty="id" 自动返回添加数据的id
		log.info("result={}",gson.toJson(add));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), add.getCode());
	}
	
	/**
	 * 测试角色编辑
	 */
	@Test
	public void edit() {
		role.setName("开发管理员");
		ResponseVo<Boolean> edit = roleService.edit(role);
		log.info("result={}",gson.toJson(edit));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), edit.getCode());
	}
	
	/**
	 * 测试根据分页获取角色数据
	 */
	@Test
	public void getPageInfoByPage() {
		ResponseVo<PageInfo> roleListByPage = roleService.getRoleListByPage(1, 1);
		log.info("result={}",gson.toJson(roleListByPage));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), roleListByPage.getCode());
	}
	
	/**
	 * 测试根据分页和查询信息获取角色数据
	 */
	@Test
	public void getPageInfoByPageAndName() {
		ResponseVo<PageInfo> roleListByPageAndName = roleService.getRoleListByPageAndName(1, 1, "超级管理员");
		log.info("result={}",gson.toJson(roleListByPageAndName));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), roleListByPageAndName.getCode());
	}
	
	/**
	 * 测试角色名称是否重名
	 */
	@Test
	public void isNameExist() {
		ResponseVo<Boolean> nameExist = roleService.isNameExist(role, role.getId());
		log.info("result={}",gson.toJson(nameExist));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), nameExist.getCode());
	}
	
	/**
	 * 判断角色权限保存
	 */
	@Test
	public void saveAuthority() {
		ResponseVo<Boolean> saveAuthority = roleService.saveAuthority("1,2,3", role.getId());
		log.info("result={}",gson.toJson(saveAuthority));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), saveAuthority.getCode());
	}
}
