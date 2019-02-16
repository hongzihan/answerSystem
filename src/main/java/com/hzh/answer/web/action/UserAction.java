package com.hzh.answer.web.action;

import com.hzh.answer.domain.SysUser;
import com.hzh.answer.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<SysUser>{
	
	private SysUser sysUser = new SysUser();
	private UserService userService;

	@Override
	public SysUser getModel() {
		// TODO Auto-generated method stub
		return sysUser;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Action中用于登录的方法
	 * @return
	 */
	public String login() {
		boolean loginSuccess = userService.login(sysUser);
		if(loginSuccess) {
			return SUCCESS;
		} else {
			return LOGIN;
		}
	}
	
	
}
