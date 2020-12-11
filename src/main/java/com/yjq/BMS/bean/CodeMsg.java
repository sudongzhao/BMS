package com.yjq.BMS.bean;
/**
 * 错误码统一处理类，所有的错误码统一定义在这里
 * @author 82320
 *
 */
public class CodeMsg {

	private Integer code;//错误码
	
	private String msg;//错误信息
	
	/**
	 * 构造函数私有化即单例模式
	 * 该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。
	 * @param code
	 * @param msg
	 */
	private CodeMsg(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public CodeMsg () {
		
	}
	
	public Integer getCode() {
		return code;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//通用错误码定义
	//处理成功消息码
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	//通用数据错误码
	public static CodeMsg DATA_ERROR = new CodeMsg(-1, "非法数据！");
	public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-2, "");
	public static CodeMsg CPACHA_EMPTY = new CodeMsg(-3, "验证码不能为空!");
	public static CodeMsg SESSION_EXPIRED = new CodeMsg(-4, "会话已失效，请刷新页面重试！");
	public static CodeMsg CPACHA_ERROR = new CodeMsg(-5, "验证码错误！");
	public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "还未登录或会话失效，请重新登录！");
	public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-7, "图片格式不正确！");
	public static CodeMsg UPLOAD_PHOTO_ERROR = new CodeMsg(-8, "图片上传失败！");
	public static CodeMsg UPLOAD_ATTACHMENT_ERROR = new CodeMsg(-9, "附件上传失败！");
	public static CodeMsg DOWNLOAD_FILE_ERROR = new CodeMsg(-10, "文件下载失败！");
	
	
	//菜单管理错误码
	public static CodeMsg MENU_STATE_CHANGE_ERROR = new CodeMsg(-1000, "菜单状态改变失败！请联系管理员！");
	public static CodeMsg MENU_ADD_ERROR = new CodeMsg(-1001, "菜单添加失败！请联系管理员！");
	public static CodeMsg MENU_EDIT_ERROR = new CodeMsg(-1002, "菜单编辑失败！请联系管理员！");
	public static CodeMsg MENU_DELETE_ERROR = new CodeMsg(-1003, "菜单删除失败！请联系管理员！");
	public static CodeMsg MENU_CHILDREN_EXIST = new CodeMsg(-1004, "删除失败！请先删除该菜单下的子菜单！");
	
	
	//管理员管理错误码
	public static CodeMsg ADMIN_STATE_CHANGE_ERROR = new CodeMsg(-2000, "管理员状态信息改变失败！请联系管理员！");
	public static CodeMsg ADMIN_MOBILE_LENGTH_ERROR = new CodeMsg(-2001, "请输入正确的管理员手机号长度！");
	public static CodeMsg ADMIN_ADD_ERROR = new CodeMsg(-2002, "管理员信息添加失败！请联系管理员！");
	public static CodeMsg ADMIN_NAME_EXIST = new CodeMsg(-2003, "管理员名字重复！请换一个！");
	public static CodeMsg ADMIN_EDIT_ERROR = new CodeMsg(-2004, "管理员信息编辑失败！请联系管理员！");
	public static CodeMsg ADMIN_DELETE_ERROR = new CodeMsg(-2005, "管理员信息删除失败！请联系管理员！");
	public static CodeMsg ADMIN_ROLE_EDIT_ERROR = new CodeMsg(-2006, "管理员对应角色信息编辑失败！请联系管理员！");
	
	
	//角色管理错误码
	public static CodeMsg ROLE_ADD_ERROR = new CodeMsg(-3000, "角色添加失败！请联系管理员！");
	public static CodeMsg ROLE_EDIT_ERROR = new CodeMsg(-3001, "角色编辑失败！请联系管理员！");
	public static CodeMsg ROLE_DELETE_ERROR = new CodeMsg(-3002, "角色删除失败！请联系管理员！");
	public static CodeMsg ROLE_NAME_EXIST = new CodeMsg(-3003, "角色名字重复！请换一个！");
	public static CodeMsg ROLE_AUTHORITY_UPDATE_ERROR = new CodeMsg(-3004, "角色权限保存失败！请联系管理员！");
	public static CodeMsg ROLE_AUTHORITY_DELETE_ERROR = new CodeMsg(-3005, "角色权限删除失败！请联系管理员！");
	
	//邮件管理错误码
	public static CodeMsg MAIL_ATTACHMENT_NO_EXIST = new CodeMsg(-4000, "删除失败！	这个附件已经不存在了！");
	public static CodeMsg MAIL_ATTACHMENT_DELETE_ERROR = new CodeMsg(-4001, "附件删除失败！请联系管理员！");
	public static CodeMsg MAIL_RECEIVER_GET_ERROR = new CodeMsg(-4002, "发送失败！收件人获取异常！请联系管理员！");
	public static CodeMsg MAIL_NO_EXIST = new CodeMsg(-4003, "删除失败！这个邮件已经不存在了！");
	public static CodeMsg MAIL_DELETE_ERROR = new CodeMsg(-4004, "删除失败！请联系管理员！");
	
	//系统管理错误码
	public static CodeMsg USERNAME_OR_PASSWORD_ERROR = new CodeMsg(-5000, "用户名或者密码错误！");
	public static CodeMsg USER_STATE_ERROR = new CodeMsg(-5001, "该用户已被冻结！无法登录！");
	public static CodeMsg USER_AUTHORITY_ERROR = new CodeMsg(-5002, "该用户没有任何权限！无法登录！");
	public static CodeMsg PERSON_INFO_SAVE_ERROR = new CodeMsg(-5003, "个人信息保存失败！请联系管理员！");
	
	//公告管理错误码
	public static CodeMsg ANNOUNCEMENT_ADD_ERROR  = new CodeMsg(-6000, "公告添加失败！请联系管理员！");
	public static CodeMsg ANNOUNCEMENT_NOT_EXIST  = new CodeMsg(-6001, "删除失败！这个公告已经不存在了！");
	public static CodeMsg ANNOUNCEMENT_DELETE_ERROR  = new CodeMsg(-6002, "公告删除失败！请联系管理员！");
}
