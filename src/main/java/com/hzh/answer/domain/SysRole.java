package com.hzh.answer.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统角色实体
 * @author Administrator
 *
 */
public class SysRole {

	/**
	 * 角色ID
	 */
	private Integer roleid;
	/**
	 * 角色名称
	 */
	private String rolename;
	/**
	 * 角色状态
	 */
	private Integer rolestate;
	/**
	 * 角色说明
	 */
	private String roledesc;
	
	/**
	 * 与用户的一对多关系
	 * @return
	 */
	private Set<SysUser> sysUsers = new HashSet<>();
	
	/**
	 * 与SysFunction的多对多关系
	 * @return
	 */
	private Set<SysFunction> sysFunctions = new HashSet<SysFunction>();
	
	public Set<SysFunction> getSysFunctions() {
		return sysFunctions;
	}
	public void setSysFunctions(Set<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}
	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Integer getRolestate() {
		return rolestate;
	}
	public void setRolestate(Integer rolestate) {
		this.rolestate = rolestate;
	}
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}
	@Override
	public String toString() {
		return "SysRole [roleid=" + roleid + ", rolename=" + rolename + ", rolestate=" + rolestate + ", roledesc="
				+ roledesc + "]";
	}
	
	
}
