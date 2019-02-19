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
	 */
	public SysUser login(SysUser user) {
		SysUser existUser = userDao.findOne(user);
		if(existUser==null) {
			return null;
		} else {
			if(existUser.getUsername().equals(user.getUsername())) {
				return existUser;
			} else {
				return null;
			}
		}
	}

}
