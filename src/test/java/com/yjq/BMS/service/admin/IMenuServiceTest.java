package com.yjq.BMS.service.admin;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yjq.BMS.BMSApplicationTest;
import com.yjq.BMS.bean.CodeMsg;
import com.yjq.BMS.dao.admin.MenuMapper;
import com.yjq.BMS.pojo.admin.Menu;
import com.yjq.BMS.vo.common.ResponseVo;

import lombok.extern.slf4j.Slf4j;
/**
 * 菜单service测试类
 * @author 82320
 *
 */
@Slf4j
//有@After的delete方法，可以不用下面的@Transactional事务回滚
//@Transactional   //在测试类中的事务回滚（即使数据都不报错也会回滚，不在数据库中留下测试数据）
public class IMenuServiceTest extends BMSApplicationTest{

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private IMenuService menuService;
	
	//把返回数据json格式化
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private Menu menu;
	
	//private Integer id; //菜单ID
	
	@Before //初始化方法   对于每一个测试方法执行前都要执行一次
	public void before() {
		log.info("添加菜单...");
		Menu menu = new Menu(null, 1,"订单列表","/home/order/list",0,"&#xe6a2;",1);
		this.menu = menu;
		add();
	}
	
	@After //释放资源  对于每一个测试方法执行后都要执行一次
	public void delete() {
		ResponseVo<Boolean> delete = menuService.delete(menu.getId());
		log.info("result={}",gson.toJson(delete));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), delete.getCode());
	}
	
	/**
	 * 测试菜单添加
	 */
	public void add() {
		ResponseVo<Boolean> add = menuService.add(menu);
		//id = menu.getId();//useGeneratedKeys="true" keyProperty="id" 自动返回添加数据的id
		log.info("result={}",gson.toJson(add));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), add.getCode());
	}
	
	/**
	 * 测试获取一级菜单
	 */
	@Test
	public void getFirstMenus() {
		List<Menu> allMenus = menuMapper.selectAll();
		ResponseVo<List<Menu>> firstMenus = menuService.getFirstMenus(allMenus);
		log.info("result={}",gson.toJson(firstMenus));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), firstMenus.getCode());
	}
	
	
	/**
	 * 测试获取二级菜单
	 */
	@Test
	public void getSecondMenus() {
		List<Menu> allMenus = menuMapper.selectAll();
		ResponseVo<List<Menu>> secondMenus = menuService.getSecondMenus(allMenus);
		log.info("result={}",gson.toJson(secondMenus));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), secondMenus.getCode());
	}
	
	/**
	 * 测试获取三级菜单
	 */
	@Test
	public void getThirdMenus() {
		List<Menu> allMenus = menuMapper.selectAll();
		ResponseVo<List<Menu>> thirdMenus = menuService.getThirdMenus(allMenus);
		log.info("result={}",gson.toJson(thirdMenus));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), thirdMenus.getCode());
	}
	
	/**
	 * 测试是否是二级菜单
	 */
	@Test
	public void isSecondMenu() {
		ResponseVo<Boolean> secondMenu = menuService.isSecondMenu(menu);
		log.info("result={}",gson.toJson(secondMenu));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), secondMenu.getCode());
	}
	
	/**
	 * 测试是否是一级菜单
	 */
	@Test
	public void isFirstMenu() {
		ResponseVo<Boolean> firstMenu = menuService.isFirstMenu(menu);
		log.info("result={}",gson.toJson(firstMenu));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), firstMenu.getCode());
	}
	
	/**
	 * 测试某个菜单是否有子菜单
	 */
	@Test
	public void haveChildrenMenu() {
		ResponseVo<Boolean> haveChildrenMenu = menuService.haveChildrenMenu(menu);
		log.info("result={}",gson.toJson(haveChildrenMenu));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), haveChildrenMenu.getCode());
	}
	

	
	/**
	 * 测试菜单编辑
	 */
	@Test
	public void edit() {
		menu.setName("商品列表");
		ResponseVo<Boolean> edit = menuService.edit(menu);
		log.info("result={}",gson.toJson(edit));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), edit.getCode());
	}
	
	/**
	 * 测试当前菜单是否是二级菜单
	 */
	@Test
	public void level() {
		ResponseVo<Integer> level = menuService.level(menu.getId());
		log.info("result={}",gson.toJson(level));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), level.getCode());
	}
	
	/**
	 * 测试更改菜单状态
	 */
	@Test
	public void chageState() {
		ResponseVo<Boolean> chageState = menuService.chageState(menu.getId());
		log.info("result={}",gson.toJson(chageState));
		Assert.assertEquals(CodeMsg.SUCCESS.getCode(), chageState.getCode());
	}
}
