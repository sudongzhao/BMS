package com.yjq.BMS.controller.admin;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.yjq.BMS.constant.SessionConstant;
import com.yjq.BMS.enums.MenuStateEnum;
import com.yjq.BMS.pojo.admin.Admin;
import com.yjq.BMS.pojo.admin.Authority;
import com.yjq.BMS.pojo.admin.Menu;
import com.yjq.BMS.service.admin.IAnnouncementService;
import com.yjq.BMS.service.admin.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yjq.BMS.dao.admin.AdminMapper;
import com.yjq.BMS.dao.admin.AnnouncementMapper;
import com.yjq.BMS.dao.admin.AttachmentMapper;
import com.yjq.BMS.dao.admin.AuthorityMapper;
import com.yjq.BMS.dao.admin.MailMapper;
import com.yjq.BMS.dao.admin.MenuMapper;
import com.yjq.BMS.dao.admin.RoleMapper;

/**
 * 后台管理系统首页控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/index")
@Controller
public class IndexController {

	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IAnnouncementService announcementService;
	
	@Autowired 
	private MenuMapper menuMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AnnouncementMapper announcementMapper;
	
	@Autowired
	private MailMapper mailMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	
	/**
	 * 系统首页页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) {
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		List<Authority> selectByRoleId = authorityMapper.selectByRoleId(loginedAdmin.getRoleId()); //获取当前用户所有权限
		Set<Integer> menuIdSet = selectByRoleId.stream().map(Authority :: getMenuId).collect(Collectors.toSet());//把权限中所有菜单id取出来
		List<Menu> allMenusByStateAndPrimaryKeys = menuMapper.selectByStateAndPrimaryKeys(MenuStateEnum.OPEN.getCode(), menuIdSet);
		model.addAttribute("FirstMenus",menuService.getFirstMenus(allMenusByStateAndPrimaryKeys).getData());
		model.addAttribute("SecondMenus",menuService.getSecondMenus(allMenusByStateAndPrimaryKeys).getData());
		model.addAttribute("ThirdMenus",menuService.getThirdMenus(allMenusByStateAndPrimaryKeys).getData());
		return "admin/index/index";
	}
	
	/**
	 * 系统欢迎页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String welcome(Model model){
		model.addAttribute("adminTotal", adminMapper.getTotal());
		model.addAttribute("announcementTotal", announcementMapper.getTotal());
		model.addAttribute("mailTotal", mailMapper.getTotal());
		model.addAttribute("roleTotal", roleMapper.getTotal());
		model.addAttribute("attachmentTotal", attachmentMapper.getTotal());
		model.addAttribute("menuTotal", menuMapper.getTotal());
		model.addAttribute("PageInfo", announcementService.getAnnouncementByPage(1, 5).getData());
		model.addAttribute("allAdmins", adminMapper.selectAll());
		return "admin/index/welcome";
	}
	
}
