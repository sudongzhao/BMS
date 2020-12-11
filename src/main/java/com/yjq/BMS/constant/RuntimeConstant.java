package com.yjq.BMS.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 系统运行时的常量
 * @author Administrator
 *
 */
public class RuntimeConstant {

	//登录拦截器无需拦截的url      Arrays.asList：字符串数组转化为List
	public static List<String> loginExcludePathPatterns = Arrays.asList(
			"/admin/system/login",
			"/common/cpacha/generate_cpacha",
			"/admin/login/**",
			"/admin/common/**",
			"/admin/X-admin-2.2/**",
			"/ueditor/**",
			"/photo/**"
		);
}
