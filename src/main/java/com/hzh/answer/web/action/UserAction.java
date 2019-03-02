package com.hzh.answer.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.EscapedErrors;

import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.SysFunction;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.domain.util.PageBean;
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
	
	// 分页参数
	private Integer currPage = 1;
	private Integer pageSize = 3;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
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
			existUser.setRolename(existUser.getSysRole().getRolename());
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
	
	/**
	 * 分页列出所有用户
	 */
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUser.class);
		if(sysUser.getUsername()!=null && !"".equals(sysUser.getUsername())) {
			detachedCriteria.add(Restrictions.like("username", "%"+ sysUser.getUsername() +"%"));
		}
		PageBean<SysUser> pageBean = userService.findAllByPage(detachedCriteria,currPage,pageSize);
		
		ActionContext.getContext().getValueStack().push(pageBean);
		return "listSuccess";
	}
	
	/**
	 * 跳转到用户添加页面
	 */
	public String add() {
		return "addUI";
	}
	
	/**
	 * 跳转到用户编辑页面
	 */
	public String edit() {
		SysUser user = userService.findOneByUserId(sysUser.getUserid());
		ActionContext.getContext().getValueStack().push(user);
		return "editUI";
	}
	
	/**
	 * 新增用户
	 */
	public String addUser() {
		Integer addStatus = userService.addUser(sysUser);
		if(addStatus.equals(1)) {
			return "addUserSuccess";
		} else {
			System.out.println("该用户已经存在或者系统发生异常");
			return "addUI";
		}
	}
	
	/**
	 * 编辑用户
	 */
	public String updateUser() {
		Integer editStatus = userService.editSubject(sysUser);
		if(editStatus.equals(1)) {
			return "editUserSuccess";
		} else {
			System.out.println("更改用户异常");
			return "editUI";
		}
	}
}
