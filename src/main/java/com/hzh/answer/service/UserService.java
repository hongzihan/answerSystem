package com.hzh.answer.service;

import com.hzh.answer.domain.SysUser;

/**
 * 用户业务接口
 * @author ken
 *
 */
public interface UserService {
	/**
	 * 登录方法
	 * @param user
	 */
	SysUser login(SysUser user);
}
