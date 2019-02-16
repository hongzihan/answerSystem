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
	 * false 表示没有查到这个用户
	 * true 表示查询到了，则返回1表示登录成功
	 */
	public boolean login(SysUser user) {
		SysUser user2 = userDao.findOne(user);
		if(user2==null) {
			return false;
		} else {
			if(user2.getUsername().equals(user.getUsername())) {
				return true;
			} else {
				return false;
			}
		}
	}

}
