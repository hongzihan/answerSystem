package com.hzh.answer.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hzh.answer.dao.SysFunctionDao;
import com.hzh.answer.domain.SysFunction;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.domain.util.PageBean;
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
		return sysFunction;
	}

	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
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
	
	/**
	 * 分页列出所有应用
	 */
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysFunction.class);
		if(sysFunction.getFunname()!=null && !"".equals(sysFunction.getFunname())) {
			detachedCriteria.add(Restrictions.like("funname", "%"+ sysFunction.getFunname() +"%"));
		}
		PageBean<SysFunction> pageBean = sysFunctionService.findAllByPage(detachedCriteria,currPage,pageSize);
		
		ActionContext.getContext().getValueStack().push(pageBean);
		return "listSuccess";
	}
	
	/**
	 * 添加功能的方法
	 */
	public String addFunction() {
		System.out.println("游戏");
		Integer addStatus = sysFunctionService
				.addFunction(sysFunction);
		if(addStatus.equals(1)) {
			System.out.println("嘻嘻");
			return "addFunctionSuccess";
		} else {
			return "addUI";
		}
	}
	
	/**
	 * 跳转到添加页面的功能
	 */
	public String add() {
		return "addUI";
	}
	
	/**
	 * 跳转到编辑页面
	 */
	public String edit() {
		SysFunction sysFunction2 = sysFunctionService.findOneByFunId(sysFunction.getFunid());
		ActionContext.getContext().getValueStack().push(sysFunction2);
		return "editUI";
	}
	
	/**
	 * 编辑功能
	 */
	public String editFunction() {
		Integer editStatus = sysFunctionService.editFunction(sysFunction);
		if(editStatus.equals(1)) {
			return "editFunctionSuccess";
		} else {
			System.out.println("更改功能异常");
			return "editUI";
		}
		
	}
}
