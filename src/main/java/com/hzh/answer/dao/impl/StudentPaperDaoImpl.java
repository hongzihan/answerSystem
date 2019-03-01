package com.hzh.answer.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;

import com.hzh.answer.dao.StudentPaperDao;
import com.hzh.answer.domain.StudentPaper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.StudentErrorSubject;

public class StudentPaperDaoImpl extends BaseDaoImpl<StudentPaper> implements StudentPaperDao {

	@Override
	public Long getRightCount(StudentPaper studentPaper) {
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(
				"select count(*) as rightCount from StudentPaper where spid=? and userid=? and studentstate = 1",
				studentPaper.getSpid(), studentPaper.getUserid());
		if (list != null) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取错题数据
	 */
	@Override
	public List<StudentErrorSubject> findErrorSubject(StudentPaper studentPaper) {
		System.out.println(studentPaper.getSpid() + "-" + studentPaper.getUserid());
		List<Object> objectList = (List<Object>) this.getHibernateTemplate().find(
				"select s1.pname,s1.userid,s1.studentkey,s2.sid,s2.scontent,s2.sa,s2.sb,s2.sc,s2.sd,s2.skey from StudentPaper s1,Subject s2 where s1.sid = s2.sid and s1.studentstate=0 and s1.spid=? and s1.userid=?",
				studentPaper.getSpid(), studentPaper.getUserid());
//		for (StudentErrorSubject studentErrorSubject : objectList) {
//			System.out.println(studentErrorSubject);
//		}
		List<StudentErrorSubject> list = new ArrayList<>();
		for (Object object : objectList) {
			Object[] objects = (Object[]) object;
			StudentErrorSubject studentErrorSubject = new StudentErrorSubject();
			studentErrorSubject.setPname((String) objects[0]);
			studentErrorSubject.setUserid( (Integer) objects[1]);
			studentErrorSubject.setStudentkey((String) objects[2]);
			studentErrorSubject.setSid( (Integer) objects[3]);
			studentErrorSubject.setScontent((String) objects[4]);
			studentErrorSubject.setSa((String) objects[5]);
			studentErrorSubject.setSb((String) objects[6]);
			studentErrorSubject.setSc((String) objects[7]);
			studentErrorSubject.setSd((String) objects[8]);
			studentErrorSubject.setSkey((String) objects[9]);
			list.add(studentErrorSubject);
		}
		return list;
	}

	/**
	 * 分页获取错题数据
	 */
	@Override
	public List<StudentErrorSubject> findErrorSubjectByPage(final StudentPaper studentPaper, final Integer begin,
			final Integer pageSize) {
		List<Object> objectList = null;
//		for (StudentErrorSubject studentErrorSubject : objectList) {
//			System.out.println(studentErrorSubject);
//		}
		Object execute = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("select s1.pname,s1.userid,s1.studentkey,s2.sid,s2.scontent,s2.sa,s2.sb,s2.sc,s2.sd,s2.skey from StudentPaper s1,Subject s2 where s1.sid = s2.sid and s1.studentstate=0 and s1.spid=? and s1.userid=?");				
				query.setParameter(0, studentPaper.getSpid());
				query.setParameter(1, studentPaper.getUserid());
				query.setFirstResult(begin);
				query.setMaxResults(pageSize);
				List<Object> list = query.list();
				return list;
			}
		});
		// 将获取到的对象转为List
		objectList = (List<Object>) execute;
		List<StudentErrorSubject> list = new ArrayList<>();
		// 遍历整个List,将值赋值进入StudentErrorSubject
		for (Object object : objectList) {
			Object[] objects = (Object[]) object;
			StudentErrorSubject studentErrorSubject = new StudentErrorSubject();
			studentErrorSubject.setPname((String) objects[0]);
			studentErrorSubject.setUserid( (Integer) objects[1]);
			studentErrorSubject.setStudentkey((String) objects[2]);
			studentErrorSubject.setSid( (Integer) objects[3]);
			studentErrorSubject.setScontent((String) objects[4]);
			studentErrorSubject.setSa((String) objects[5]);
			studentErrorSubject.setSb((String) objects[6]);
			studentErrorSubject.setSc((String) objects[7]);
			studentErrorSubject.setSd((String) objects[8]);
			studentErrorSubject.setSkey((String) objects[9]);
			list.add(studentErrorSubject);
		}
		return list;
	}

	@Override
	public List<StudentErrorSubject> findAllErrorSubjectByPage(final StudentPaper studentPaper, final Integer begin,
			final Integer pageSize) {
		List<Object> objectList = null;
//		for (StudentErrorSubject studentErrorSubject : objectList) {
//			System.out.println(studentErrorSubject);
//		}
		Object execute = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("select s1.pname,s1.userid,s1.studentkey,s2.sid,s2.scontent,s2.sa,s2.sb,s2.sc,s2.sd,s2.skey from StudentPaper s1,Subject s2 where s1.sid = s2.sid and s1.studentstate=0 and s1.userid=?");				
				query.setParameter(0, studentPaper.getUserid());
				query.setFirstResult(begin);
				query.setMaxResults(pageSize);
				List<Object> list = query.list();
				return list;
			}
		});
		// 将获取到的对象转为List
		objectList = (List<Object>) execute;
		List<StudentErrorSubject> list = new ArrayList<>();
		// 遍历整个List,将值赋值进入StudentErrorSubject
		for (Object object : objectList) {
			Object[] objects = (Object[]) object;
			StudentErrorSubject studentErrorSubject = new StudentErrorSubject();
			studentErrorSubject.setPname((String) objects[0]);
			studentErrorSubject.setUserid( (Integer) objects[1]);
			studentErrorSubject.setStudentkey((String) objects[2]);
			studentErrorSubject.setSid( (Integer) objects[3]);
			studentErrorSubject.setScontent((String) objects[4]);
			studentErrorSubject.setSa((String) objects[5]);
			studentErrorSubject.setSb((String) objects[6]);
			studentErrorSubject.setSc((String) objects[7]);
			studentErrorSubject.setSd((String) objects[8]);
			studentErrorSubject.setSkey((String) objects[9]);
			list.add(studentErrorSubject);
		}
		return list;
	}

	@Override
	public List<StudentErrorSubject> findAllErrorSubject(StudentPaper studentPaper) {
		System.out.println(studentPaper.getSpid() + "-" + studentPaper.getUserid());
		List<Object> objectList = (List<Object>) this.getHibernateTemplate().find(
				"select s1.pname,s1.userid,s1.studentkey,s2.sid,s2.scontent,s2.sa,s2.sb,s2.sc,s2.sd,s2.skey from StudentPaper s1,Subject s2 where s1.sid = s2.sid and s1.studentstate=0 and s1.userid=?",
				studentPaper.getUserid());
//		for (StudentErrorSubject studentErrorSubject : objectList) {
//			System.out.println(studentErrorSubject);
//		}
		List<StudentErrorSubject> list = new ArrayList<>();
		for (Object object : objectList) {
			Object[] objects = (Object[]) object;
			StudentErrorSubject studentErrorSubject = new StudentErrorSubject();
			studentErrorSubject.setPname((String) objects[0]);
			studentErrorSubject.setUserid( (Integer) objects[1]);
			studentErrorSubject.setStudentkey((String) objects[2]);
			studentErrorSubject.setSid( (Integer) objects[3]);
			studentErrorSubject.setScontent((String) objects[4]);
			studentErrorSubject.setSa((String) objects[5]);
			studentErrorSubject.setSb((String) objects[6]);
			studentErrorSubject.setSc((String) objects[7]);
			studentErrorSubject.setSd((String) objects[8]);
			studentErrorSubject.setSkey((String) objects[9]);
			list.add(studentErrorSubject);
		}
		return list;
	}

}
