package com.hzh.answer.web.action;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.EscapedErrors;

import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.service.PaperService;
import com.hzh.answer.service.SubjectService;
import com.hzh.answer.service.UserService;
import com.opensymphony.xwork2.ActionContext;
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
	
	private Boolean isAdmin = false;
	
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * Action中用于登录的方法
	 * @return
	 */
	public String login() {
		Integer loginStatus = userService.login(sysUser);
		if(loginStatus.equals(1) && !isAdmin) {
			// 普通学生登录
			// 这里需要将查询到的user存入session
			SysUser existUser = userService.findOneBySimpleUser(sysUser);
			ActionContext.getContext().getSession().put("existUser",existUser);
			return "normalLoginSuccess";
		} else if(loginStatus.equals(-1) && isAdmin){
			// 管理员登录
			SysUser existUser = userService.findOneBySimpleUser(sysUser);
			ActionContext.getContext().getSession().put("existUser",existUser);
			return "adminLoginSuccess";
		} else {
			return LOGIN;
		}
	}
	
	/**
	 * 注销的方法
	 */
	public String logout() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("existUser");
		return "logout";
	}
	
}
