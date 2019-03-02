package com.hzh.answer.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hzh.answer.dao.SysFunctionDao;
import com.hzh.answer.dao.SysRoleDao;
import com.hzh.answer.domain.SysFunction;
import com.hzh.answer.domain.SysRole;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.service.SysRoleService;

/**
 * 系统角色的Service层实现
 * @author ken
 *
 */
@Transactional
public class SysRoleServiceImpl implements SysRoleService {
	private SysRoleDao sysRoleDao;
	private SysFunctionDao sysFunctionDao;

	public SysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}
	
	public SysFunctionDao getSysFunctionDao() {
		return sysFunctionDao;
	}

	public void setSysFunctionDao(SysFunctionDao sysFunctionDao) {
		this.sysFunctionDao = sysFunctionDao;
	}

	/**
	 * 分页查询角色的业务
	 */
	@Override
	public PageBean<SysRole> findAllByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SysRole> pageBean = new PageBean<SysRole>();
		// 设置当前页数
		pageBean.setCurrPage(currPage);
		// 设置每页记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = sysRoleDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置数据集合
		Integer begin = (currPage - 1) * pageSize;
		List<SysRole> list = sysRoleDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 添加系统角色的业务
	 * 0,-1表示失败
	 * 1表示成功
	 */
	@Override
	public Integer addSysRole(SysRole sysRole) {
		Integer addStatus = 0;
		SysRole existSysRole = sysRoleDao.findByRolename(sysRole.getRolename());
		if(existSysRole != null) {
			addStatus = -1;
		} else {
			try {
				sysRoleDao.save(sysRole);
				addStatus = 1;
			} catch (Exception e) {
				e.printStackTrace();
				addStatus = -1;
			}
		}
		return addStatus;
	}

	/**
	 * 根据id查询单个角色的业务
	 */
	@Override
	public SysRole findOneByRoleId(Integer roleid) {
		try {
			SysRole sysRole = sysRoleDao.findById(roleid);
			return sysRole;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 修改角色的业务
	 */
	@Override
	public Integer editSysRole(SysRole sysRole) {
		Integer editStatus = 0;
		try {
			sysRoleDao.update(sysRole);
			editStatus = 1;
		} catch (Exception e) {
			e.printStackTrace();
			editStatus = -1;
		}
		return editStatus;
	}

	/**
	 * 保存权限业务
	 */
	@Override
	public Integer saveRight(String[] funids, Integer roleid) {
		Integer saveStatus = 0;
		try {
			// 查出需要保存的Funtion
			List<SysFunction> functionList = new ArrayList<SysFunction>();
			for (String funid : funids) {
				SysFunction sysFunction = sysFunctionDao.findById(Integer.parseInt(funid));
				functionList.add(sysFunction);
			}
			// 查出对应的角色
			SysRole sysRole = sysRoleDao.findById(roleid);
			sysRole.setSysFunctions(new HashSet<SysFunction>(functionList));
			// 级联更新
			System.out.println("以下是更新");
			sysRoleDao.update(sysRole);
			System.out.println("以上也是更新");
			saveStatus = 1;
		} catch (Exception e) {
			e.printStackTrace();
			saveStatus = -1;
		}
		return saveStatus;
	}
	
	
}
