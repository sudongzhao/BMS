package com.yjq.BMS.controller.admin;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.yjq.BMS.constant.SessionConstant;
import com.yjq.BMS.dao.admin.AdminMapper;
import com.yjq.BMS.dao.admin.AuthorityMapper;
import com.yjq.BMS.dao.admin.MenuMapper;
import com.yjq.BMS.enums.MenuStateEnum;
import com.yjq.BMS.pojo.admin.Admin;
import com.yjq.BMS.pojo.admin.Announcement;
import com.yjq.BMS.pojo.admin.Authority;
import com.yjq.BMS.pojo.admin.Menu;
import com.yjq.BMS.service.admin.IAnnouncementService;
import com.yjq.BMS.service.admin.IMenuService;
import com.yjq.BMS.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;

/**
 * 后台管理系统公告控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/announcement")
@Controller
public class AnnouncementController {

	@Autowired
	private IMenuService menuService;
	
	@Autowired 
	private MenuMapper menuMapper;
	
	@Autowired
	private IAnnouncementService announcementService;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	/**
	 * 管理员列表页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,Integer id,String content,HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize //每页5个数据
			) {
		//获取列表展示有关信息
		if(StringUtil.isEmpty(content)) {
			//如果查询信息为空
			model.addAttribute("PageInfo", announcementService.getAnnouncementByPage(pageNum, pageSize).getData());
		}else {
			model.addAttribute("PageInfo", announcementService.getAnnouncementByPageAndContent(pageNum, pageSize, content).getData());
			model.addAttribute("content",content);
		}
		//获取路径上有关信息
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		List<Authority> selectByRoleId = authorityMapper.selectByRoleId(loginedAdmin.getRoleId()); //获取当前用户所有权限
		Set<Integer> menuIdSet = selectByRoleId.stream().map(Authority :: getMenuId).collect(Collectors.toSet());//把权限中所有菜单id取出来
		List<Menu> allMenusByStateAndPrimaryKeys = menuMapper.selectByStateAndPrimaryKeys(MenuStateEnum.OPEN.getCode(), menuIdSet);
		model.addAttribute("allAdmins", adminMapper.selectAll());
		model.addAttribute("onThirdMenus", menuService.getThirdMenus(allMenusByStateAndPrimaryKeys).getData());
		model.addAttribute("parentMenu", menuMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId()));
		model.addAttribute("currentMenu", selectByPrimaryKey);
		return "admin/announcement/index";
	}
	
	

	/**
	 * 公告添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		return "admin/announcement/add";
	}
	
	/**
	 * 公告添加表单处理
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> add(Announcement announcement, HttpServletRequest request){
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		return announcementService.add(announcement, loginedAdmin.getId());
	}
	
	/**
	 * 公告删除处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> delete(Integer id){
		return announcementService.delete(id);
	}
}
