package com.hzh.answer.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.domain.SysRole;
import com.hzh.answer.domain.util.PageBean;

/**
 * 系统角色的Service层
 * @author ken
 *
 */
public interface SysRoleService {

	PageBean<SysRole> findAllByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Integer addSysRole(SysRole sysRole);

	SysRole findOneByRoleId(Integer roleid);

	Integer editSysRole(SysRole sysRole);

	Integer saveRight(String[] funids, Integer roleid);

}
