package com.hzh.answer.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.domain.SysUser;
import com.hzh.answer.domain.util.PageBean;

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
	Integer login(SysUser user);

	SysUser findOneBySimpleUser(SysUser sysUser);

	PageBean<SysUser> findAllByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	SysUser findOneByUserId(Integer userid);

	Integer addUser(SysUser sysUser);

	Integer editSubject(SysUser sysUser);
}
