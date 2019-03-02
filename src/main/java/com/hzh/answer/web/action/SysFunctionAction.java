package com.hzh.answer.web.action;

import com.hzh.answer.domain.SysFunction;
import com.hzh.answer.service.SysFunctionService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
	
	
}
