package com.hzh.answer.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.dao.SysRoleDao;
import com.hzh.answer.domain.SysRole;

/**
 * 系统角色的DAO层实现
 * @author ken
 *
 */
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole> implements SysRoleDao {

	/**
	 * 根据角色名查找
	 */
	@Override
	public SysRole findByRolename(String rolename) {
		List<SysRole> list = (List<SysRole>) this.getHibernateTemplate().find("from SysRole where rolename=?", rolename);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
}
