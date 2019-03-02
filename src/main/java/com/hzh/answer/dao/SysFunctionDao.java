package com.hzh.answer.dao;

import com.hzh.answer.domain.SysFunction;

/**
 * 系统应用Dao层接口
 * @author ken
 *
 */
public interface SysFunctionDao extends BaseDao<SysFunction> {

	SysFunction findByFunname(String funname);

}
