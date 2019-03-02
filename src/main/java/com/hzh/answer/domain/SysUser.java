package com.hzh.answer.domain;
/**
 * 用户实体
 * @author Administrator
 *
 */
public class SysUser {

	/**
	 * 注意写注释
	 */
	private Integer userid;
	private String username;
	private String usertruename;
	private String userpwd;
	private Integer userstate;
	
	private String rolename;
	
	private Integer user_role_id; // 外键，方便存储
	
	/**
	 * 与角色的多对一关系
	 */
	private SysRole sysRole;
	
	public Integer getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(Integer user_role_id) {
		this.user_role_id = user_role_id;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsertruename() {
		return usertruename;
	}

	public void setUsertruename(String usertruename) {
		this.usertruename = usertruename;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public Integer getUserstate() {
		return userstate;
	}

	public void setUserstate(Integer userstate) {
		this.userstate = userstate;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "SysUser [userid=" + userid + ", username=" + username + ", usertruename="
				+ usertruename + ", userpwd=" + userpwd + ", userstate=" + userstate + ", rolename=" + rolename + "]";
	}
	
	
}
