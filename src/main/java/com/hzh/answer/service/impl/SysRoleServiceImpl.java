package com.hzh.answer.service.impl;

import com.hzh.answer.dao.SysRoleDao;
import com.hzh.answer.service.SysRoleService;

/**
 * 系统角色的Service层实现
 * @author ken
 *
 */
public class SysRoleServiceImpl implements SysRoleService {
	private SysRoleDao sysRoleDao;

	public SysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}
	
	
}
