package com.hzh.answer.dao.impl;

import java.util.List;


import com.hzh.answer.dao.SysFunctionDao;
import com.hzh.answer.domain.SysFunction;

/**
 * 系统应用Dao层实现
 * @author ken
 *
 */
public class SysFunctionDaoImpl extends BaseDaoImpl<SysFunction> implements SysFunctionDao {

	@Override
	public SysFunction findByFunname(String funname) {
		List<SysFunction> list = (List<SysFunction>) this.getHibernateTemplate().find("from SysFunction where funname=?", funname);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
