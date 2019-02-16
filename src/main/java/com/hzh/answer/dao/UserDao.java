package com.hzh.answer.dao;

import com.hzh.answer.domain.SysUser;

/**
 * 用户的Dao层
 * @author ken
 *
 */
public interface UserDao extends BaseDao<SysUser>{
	SysUser findOne(SysUser sysUser);
}
