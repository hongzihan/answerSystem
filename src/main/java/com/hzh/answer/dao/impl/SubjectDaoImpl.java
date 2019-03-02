package com.hzh.answer.dao.impl;

import java.util.List;


import com.hzh.answer.dao.SubjectDao;
import com.hzh.answer.domain.Subject;

public class SubjectDaoImpl extends BaseDaoImpl<Subject> implements SubjectDao {

	/**
	 * 根据题目名字查找题目的方法
	 */
	@Override
	public Subject findOneByScontent(String scontent) {
		List<Subject> list = (List<Subject>) this.getHibernateTemplate().find("from Subject where scontent = ?", scontent);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
