package com.hzh.answer.web.action;

import java.util.List;

import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.service.PaperService;
import com.hzh.answer.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<SysUser>{
	
	private SysUser sysUser = new SysUser();
	private UserService userService;
	private PaperService paperService;

	@Override
	public SysUser getModel() {
		// TODO Auto-generated method stub
		return sysUser;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}

	/**
	 * Action中用于登录的方法
	 * @return
	 */
	public String login() {
		SysUser existUser = userService.login(sysUser);
		if(existUser != null) {
			// 这里需要将查询到的user存入session
			ActionContext.getContext().getSession().put("existUser",existUser);
			findAllPaper();
			return SUCCESS;
		} else {
			return LOGIN;
		}
	}
	
	/**
	 * 查询所有试卷信息
	 */
	public void findAllPaper() {
		List<Paper> list = paperService.findAllWithItemCount();
		for (Paper paper : list) {
			System.out.println(paper);
		}
		ActionContext.getContext().getValueStack().set("paperList",list);
	}
	
	
}
