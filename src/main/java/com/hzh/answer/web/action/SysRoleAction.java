package com.hzh.answer.web.action;

import com.hzh.answer.domain.SysRole;
import com.hzh.answer.service.SysRoleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SysRoleAction extends ActionSupport implements ModelDriven<SysRole> {
	
	private SysRoleService sysRoleService;
	
	private SysRole sysRole = new SysRole();

	@Override
	public SysRole getModel() {
		// TODO Auto-generated method stub
		return sysRole;
	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	
}
