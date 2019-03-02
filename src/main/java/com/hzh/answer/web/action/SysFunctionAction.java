package com.hzh.answer.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hzh.answer.domain.SysFunction;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.service.SysFunctionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class SysFunctionAction extends ActionSupport implements ModelDriven<SysFunction> {
	
	private SysFunction sysFunction = new SysFunction();
	
	private SysFunctionService sysFunctionService;
	
	@Override
	public SysFunction getModel() {
		// TODO Auto-generated method stub
		return sysFunction;
	}

	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}
	
	/**
	 * 查找管理员所对应的所有功能
	 * @return
	 */
	public String findAllFunction() {
		// 从Session中把存入的existUser取出
		SysUser existUser = (SysUser) ActionContext.getContext().getSession().get("existUser");
		// 取出existUser关联的SysFunctions的Set集合并转为List集合
		Set<SysFunction> sysFunctions = existUser.getSysRole().getSysFunctions();
		List<SysFunction> sysFunctionList = new ArrayList<SysFunction>(sysFunctions);
		// 将menuList放入值栈中
		ActionContext.getContext().getValueStack().set("menuList",sysFunctionList);
		return "findAllFunctionSuccess";
	}
}
