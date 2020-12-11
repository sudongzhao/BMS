package com.yjq.BMS.controller.admin;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.yjq.BMS.bean.CodeMsg;
import com.yjq.BMS.constant.SessionConstant;
import com.yjq.BMS.dao.admin.AdminMapper;
import com.yjq.BMS.dao.admin.AuthorityMapper;
import com.yjq.BMS.dao.admin.MenuMapper;
import com.yjq.BMS.dao.admin.RoleMapper;
import com.yjq.BMS.enums.MenuStateEnum;
import com.yjq.BMS.pojo.admin.Admin;
import com.yjq.BMS.pojo.admin.Authority;
import com.yjq.BMS.pojo.admin.Menu;
import com.yjq.BMS.service.admin.IAdminService;
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
 * 后台管理系统管理员控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/admin")
@Controller
public class AdminController {

	@Autowired
	private IMenuService menuService;
	
	@Autowired 
	private MenuMapper menuMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	/**
	 * 管理员列表页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,Integer id,String name,HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize //每页5个数据
			) {
		//获取列表展示有关信息
		if(StringUtil.isEmpty(name)) {
			//如果查询信息为空
			model.addAttribute("PageInfo", adminService.getAdminListByPage(pageNum, pageSize).getData());
		}else {
			model.addAttribute("PageInfo", adminService.getAdminListByPageAndName(pageNum, pageSize, name).getData());
			model.addAttribute("name",name);
		}
		model.addAttribute("RoleList", roleMapper.selectAll());
		//获取路径上有关信息
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		List<Authority> selectByRoleId = authorityMapper.selectByRoleId(loginedAdmin.getRoleId()); //获取当前用户所有权限
		Set<Integer> menuIdSet = selectByRoleId.stream().map(Authority :: getMenuId).collect(Collectors.toSet());//把权限中所有菜单id取出来
		List<Menu> allMenusByStateAndPrimaryKeys = menuMapper.selectByStateAndPrimaryKeys(MenuStateEnum.OPEN.getCode(), menuIdSet);
		model.addAttribute("onThirdMenus", menuService.getThirdMenus(allMenusByStateAndPrimaryKeys).getData());
		model.addAttribute("parentMenu", menuMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId()));
		model.addAttribute("currentMenu", selectByPrimaryKey);
		return "admin/admin/index";
	}
	
	/**
	 * 管理员添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("RoleList", roleMapper.selectAll());
		return "admin/admin/add";
	}
	
	/**
	 * 管理员编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,Integer id) {
		Admin selectByPrimaryKey = adminMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		model.addAttribute("RoleList", roleMapper.selectAll());
		model.addAttribute("editAdmin", selectByPrimaryKey);
		return "admin/admin/edit";
	}
	
	/**
	 * 管理员添加表单处理
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> add(Admin admin){
		return adminService.add(admin);
	}
	
	/**
	 * 管理员编辑表单处理
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> edit(Admin admin,HttpServletRequest request){
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		ResponseVo<Admin> editAdmin = adminService.edit(admin);
		if(editAdmin.getCode().intValue() == CodeMsg.SUCCESS.getCode()) {
			if(loginedAdmin.getId().intValue() == editAdmin.getData().getId().intValue()) {
				//更新权限
				request.getSession().setAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY, editAdmin.getData());
			}
			return ResponseVo.successByMsg(true, "编辑成功！");
		}else {
			CodeMsg codeMsg = new CodeMsg();
			codeMsg.setCode(editAdmin.getCode());
			codeMsg.setMsg(editAdmin.getMsg());
			return ResponseVo.errorByMsg(codeMsg);
		}
	}
	
	/**
	 * 管理员删除处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> delete(Integer id){
		return adminService.delete(id);
	}
	
	
	/**
	 * 管理员更改状态处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/change_state",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> chageState(Integer id){
		return adminService.chageState(id);
	}
}
