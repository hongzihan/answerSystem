package com.hzh.answer.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hzh.answer.dao.SysRoleDao;
import com.hzh.answer.dao.UserDao;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.SysRole;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.service.UserService;

/**
 * 用户业务层实现
 * @author ken
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private SysRoleDao sysRoleDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}



	@Override
	/**
	 * 用户登录实现
	 * -1 超级管理员登录
	 * 0 失败
	 * 1 普通学生登录
	 * 2 账号密码错误
	 */
	public Integer login(SysUser user) {
		Integer loginStatus = 0;
		try {
			SysUser existUser = userDao.findOne(user);
			if(existUser!=null) {
				if(existUser.getSysRole().getRoleid().equals(1)) {
					loginStatus = 1;
				} else if(existUser.getSysRole().getRoleid().equals(-1)) {
					loginStatus = -1;
				} else {
					loginStatus = 2;
				}
			} else {
				// 如果没有异常就是账号密码错误
				loginStatus = 2;
			}
		} catch (Exception e) {
			// 出现异常，直接返回loginStatus表示系统级异常
			e.printStackTrace();
			return loginStatus;
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

	@Override
	public PageBean<SysUser> findAllByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SysUser> pageBean = new PageBean<SysUser>();
		// 设置当前页数
		pageBean.setCurrPage(currPage);
		// 设置每页记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = userDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置数据集合
		Integer begin = (currPage - 1) * pageSize;
		List<SysUser> list = userDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 根据id查询用户的业务
	 */
	@Override
	public SysUser findOneByUserId(Integer userid) {
		try {
			SysUser sysUser = userDao.findById(userid);
			return sysUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 添加用户的业务
	 * 0,-1表示失败
	 * 1表示成功
	 */
	@Override
	public Integer addUser(SysUser sysUser) {
		Integer addStatus = 0;
		System.out.println(sysUser.getUser_role_id() + "有了？");
		SysUser existUser = userDao.findByUsername(sysUser.getUsername());
		if(existUser != null) {
			addStatus = -1;
		} else {
			try {
				SysRole sysRole = sysRoleDao.findById(sysUser.getUser_role_id());
				sysUser.setSysRole(sysRole);
				userDao.save(sysUser);
				addStatus = 1;
			} catch (Exception e) {
				e.printStackTrace();
				addStatus = -1;
			}
		}
		return addStatus;
	}

	/**
	 * 修改用户的业务
	 * 0,-1表示失败
	 * 1表示成功
	 */
	@Override
	public Integer editSubject(SysUser sysUser) {
		Integer editStatus = 0;
		try {
			SysRole sysRole = sysRoleDao.findById(sysUser.getUser_role_id());
			sysUser.setSysRole(sysRole);
			userDao.update(sysUser);
			editStatus = 1;
		} catch (Exception e) {
			e.printStackTrace();
			editStatus = -1;
		}
		return editStatus;
	}

}
