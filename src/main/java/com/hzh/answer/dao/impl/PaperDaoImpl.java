package com.hzh.answer.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.dao.PaperDao;
import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.Subject;

public class PaperDaoImpl extends BaseDaoImpl<Paper> implements PaperDao {
	
	@Override
	public List<Subject> findByPname(String pname) {
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select s1.sid,s1.scontent,s1.sa,s1.sb,s1.sc,s1.sd,s1.skey,s1.sstate from Paper p1,Subject s1 where p1.sid = s1.sid and p1.pname=?", pname);
		List<Subject> subjectsList = new ArrayList<Subject>();
		for (Object object : list) {
			Object[] objects = (Object[]) object;
			Subject subject = new Subject();
			subject.setSid((Integer) objects[0]);
			subject.setScontent((String) objects[1]);
			subject.setSa((String) objects[2]);
			subject.setSb((String) objects[3]);
			subject.setSc((String) objects[4]);
			subject.setSd((String) objects[5]);
			subject.setSkey((String) objects[6]);
			subject.setSstate((Integer) objects[7]);
			subjectsList.add(subject);
		}
		return subjectsList;
	}

	@Override
	public Paper findOneByPname(String pname) {
		List<Paper> list = (List<Paper>) this.getHibernateTemplate().find("from Paper where pname=?", pname);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
