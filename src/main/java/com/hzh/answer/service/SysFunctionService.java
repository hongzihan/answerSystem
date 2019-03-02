package com.hzh.answer.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.domain.SysFunction;
import com.hzh.answer.domain.util.PageBean;

/**
 * 系统应用Service接口
 * @author ken
 *
 */
public interface SysFunctionService {

	PageBean<SysFunction> findAllByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	SysFunction findOneByFunId(Integer funid);

	Integer addFunction(SysFunction sysFunction);

	Integer editFunction(SysFunction sysFunction);

}
