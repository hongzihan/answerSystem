package com.hzh.answer.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hzh.answer.domain.SysFunction;
import com.hzh.answer.domain.SysRole;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.service.SysRoleService;
import com.opensymphony.xwork2.ActionContext;
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
		if(pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 分页列出所有角色
	 */
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysRole.class);
		if(sysRole.getRolename()!=null && !"".equals(sysRole.getRolename())) {
			detachedCriteria.add(Restrictions.like("rolename", "%"+ sysRole.getRolename() +"%"));
		}
		PageBean<SysRole> pageBean = sysRoleService.findAllByPage(detachedCriteria,currPage,pageSize);
		
		ActionContext.getContext().getValueStack().push(pageBean);
		//System.out.println("野性的我，藏于此件");
//		List<SysRole> list = pageBean.getList();
//		for (SysRole sysRole : list) {
//			System.out.println(sysRole);
//		}
//		return NONE;
		return "listSuccess";
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String add() {
		return "addUI";
	}
	
	/**
	 * 跳转到权限分配页面
	 */
	public String rightUI() {
		// 从Session中把存入的existUser取出
		SysUser existUser = (SysUser) ActionContext.getContext().getSession().get("existUser");
		// 取出existUser关联的SysFunctions的Set集合并转为List集合
		Set<SysFunction> sysFunctions = existUser.getSysRole().getSysFunctions();
		List<SysFunction> sysFunctionList = new ArrayList<SysFunction>(sysFunctions);
		// 将list放入值栈中
		ActionContext.getContext().getValueStack().set("list",sysFunctionList);
		ActionContext.getContext().getValueStack().push(sysRole);
		return "rightUI";
	}
	
	/**
	 * 跳转到编辑页面
	 */
	public String edit() {
		SysRole sysRole2 = sysRoleService.findOneByRoleId(sysRole.getRoleid());
		ActionContext.getContext().getValueStack().push(sysRole2);
		return "editUI";
	}
	
	/**
	 * 添加系统角色
	 */
	public String addSysRole() {
		System.out.println("有问题吗");
		Integer addStatus = sysRoleService.addSysRole(sysRole);
		if(addStatus.equals(1)) {
			return "addSysRoleSuccess";
		} else {
			System.out.println("角色已经存在或系统异常");
			return "addUI"; 
		}
	}
	
	/**
	 * 编辑系统角色
	 */
	public String editSysRole() {
		Integer editStatus = sysRoleService.editSysRole(sysRole);
		if(editStatus.equals(1)) {
			return "editSysRoleSuccess";
		} else {
			return "editUI";
		}
	}
	
	/**
	 * 保存权限，通过级联的方式保存
	 * @return
	 */
	public String saveRight() {
		String[] funids = ServletActionContext.getRequest().getParameterValues("ckrr");
		for (String string : funids) {
			System.out.println(string);
		}
		System.out.println("roleid=" + sysRole.getRoleid());
		Integer saveStatus = sysRoleService.saveRight(funids,sysRole.getRoleid());
		if(saveStatus.equals(1)) {
			return "saveRightSuccess";
		}
		return "rightUI";
	}
}
