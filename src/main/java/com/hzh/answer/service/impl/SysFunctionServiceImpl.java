package com.hzh.answer.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hzh.answer.dao.SysFunctionDao;
import com.hzh.answer.domain.SysFunction;
import com.hzh.answer.domain.SysRole;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.service.SysFunctionService;

/**
 * 系统应用Service层实现类
 * @author ken
 *
 */
@Transactional
public class SysFunctionServiceImpl implements SysFunctionService {
	private SysFunctionDao sysFunctionDao;

	public SysFunctionDao getSysFunctionDao() {
		return sysFunctionDao;
	}

	public void setSysFunctionDao(SysFunctionDao sysFunctionDao) {
		this.sysFunctionDao = sysFunctionDao;
	}

	/**
	 * 分页查询功能的业务
	 */
	@Override
	public PageBean<SysFunction> findAllByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SysFunction> pageBean = new PageBean<SysFunction>();
		// 设置当前页数
		pageBean.setCurrPage(currPage);
		// 设置每页记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = sysFunctionDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置数据集合
		Integer begin = (currPage - 1) * pageSize;
		List<SysFunction> list = sysFunctionDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * 查询单一功能的业务
	 */
	@Override
	public SysFunction findOneByFunId(Integer funid) {
		SysFunction sysFunction = sysFunctionDao.findById(funid);
		return sysFunction;
	}
	
	/**
	 * 添加功能的业务
	 * 0,-1表示失败
	 * 1表示成功
	 */
	@Override
	public Integer addFunction(SysFunction sysFunction) {
		Integer addStatus = 0;
		SysFunction existSysFunction = sysFunctionDao.findByFunname(sysFunction.getFunname());
		if(existSysFunction != null) {
			addStatus = -1;
		} else {
			try {
				sysFunctionDao.save(sysFunction);
				addStatus = 1;
			} catch (Exception e) {
				e.printStackTrace();
				addStatus = -1;
			}
		}
		return addStatus;
	}
	
	/**
	 * 修改功能的业务
	 * 0,-1表示失败
	 * 1表示成功
	 */
	@Override
	public Integer editFunction(SysFunction sysFunction) {
		Integer editStatus = 0;
		try {
			sysFunctionDao.update(sysFunction);
			editStatus = 1;
		} catch (Exception e) {
			e.printStackTrace();
			editStatus = -1;
		}
		return editStatus;
	}
}
