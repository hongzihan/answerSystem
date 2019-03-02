package com.hzh.answer.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.dao.UserDao;
import com.hzh.answer.domain.SysUser;

/**
 * 用户的Dao层实现类
 * @author ken
 *
 */
public class UserDaoImpl extends BaseDaoImpl<SysUser> implements UserDao {

	@Override
	/**
	 * 实现查询单一用户的方法
	 */
	public SysUser findOne(SysUser sysUser) {
		List<SysUser> list = (List<SysUser>) this.getHibernateTemplate().find("from SysUser where username=? and userpwd=?", sysUser.getUsername(), sysUser.getUserpwd());
		if(!list.isEmpty()) {
			return (SysUser)list.get(0);
		}
		return null;
	}


}
