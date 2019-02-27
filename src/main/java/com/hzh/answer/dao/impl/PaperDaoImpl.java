package com.hzh.answer.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.dao.PaperDao;
import com.hzh.answer.domain.Paper;

public class PaperDaoImpl extends BaseDaoImpl<Paper> implements PaperDao {
	
	@Override
	public List<Paper> findAllWithItemCount() {
		List<Object> listObject = (List<Object>) this.getHibernateTemplate().find("select p.pname,count(*) as scount from Paper p group by p.pname");
		List<Paper> list = new ArrayList<Paper>();
		for (Object object : listObject) {
			Object[] tempObjects = (Object[]) object;
			Paper paper = new Paper();
			paper.setPname((String) tempObjects[0]);
			paper.setScount((Long) tempObjects[1]);
			list.add(paper);
		}
		return list;
	}

}
