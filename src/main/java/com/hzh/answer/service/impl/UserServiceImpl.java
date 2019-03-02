package com.hzh.answer.service.impl;

import com.hzh.answer.dao.UserDao;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.service.UserService;

/**
 * 用户业务层实现
 * @author ken
 *
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	/**
	 * 用户登录实现
	 * -1 超级管理员登录
	 * 0 失败
	 * 1 普通学生登录
	 */
	public Integer login(SysUser user) {
		Integer loginStatus = 0;
		SysUser existUser = userDao.findOne(user);
		if(existUser.getSysRole().getRoleid().equals(1)) {
			if(existUser.getUsername().equals(user.getUsername()) && existUser.getUserpwd().equals(user.getUserpwd())) {
				loginStatus = 1;
			} else {
				loginStatus = 0;
			}
		} else if(existUser.getSysRole().getRoleid().equals(-1)) {
			if(existUser.getUsername().equals(user.getUsername()) && existUser.getUserpwd().equals(user.getUserpwd())) {
				loginStatus = -1;
			} else {
				loginStatus = 0;
			}
		} else {
			loginStatus = 0;
		}
		
		return loginStatus;
	}

	@Override
	public SysUser findOneBySimpleUser(SysUser sysUser) {
		SysUser existUser = userDao.findOne(sysUser);
		if(existUser != null) {
			return existUser;
		}
		return null;
	}

}
