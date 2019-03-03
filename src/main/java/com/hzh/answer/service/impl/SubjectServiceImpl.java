package com.hzh.answer.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hzh.answer.dao.PaperDao;
import com.hzh.answer.dao.SubjectDao;
import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.service.SubjectService;

@Transactional
public class SubjectServiceImpl implements SubjectService{
	private SubjectDao subjectDao;
	private PaperDao paperDao;

	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	@Override
	public PageBean<Subject> findAllByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Subject> pageBean = new PageBean<Subject>();
		// 设置当前页数
		pageBean.setCurrPage(currPage);
		// 设置每页记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = subjectDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 设置数据集合
		Integer begin = (currPage - 1) * pageSize;
		List<Subject> list = subjectDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	/**
	 * 新增题目的业务
	 * 0,-1表示失败
	 * 1表示成功
	 */
	@Override
	public Integer saveSubject(Subject subject, String pname) {
		Integer saveStatus = 0;
		Subject existSubject = subjectDao.findOneByScontent(subject.getScontent());
		Paper paper = paperDao.findPaperByPname(pname);
		if(existSubject != null) {
			saveStatus = -1; 
		} else {
			try {
				subject.getPapers().add(paper);
				subjectDao.save(subject);
				saveStatus = 1;
			} catch (Exception e) {
				e.printStackTrace();
				saveStatus = -1;
			}
		}
		return saveStatus;
	}

	/**
	 * 查找单个题目的业务
	 */
	@Override
	public Subject findOneBySid(Integer sid) {
		Subject subject = subjectDao.findById(sid);
		return subject;
	}

	/**
	 * 修改题目的业务
	 */
	@Override
	public Integer editSubject(Subject subject) {
		Integer editStatus = 0;
		try {
			subjectDao.update(subject);
			editStatus = 1;
		} catch (Exception e) {
			e.printStackTrace();
			editStatus = -1;
		}
		return editStatus;
	}
	
}
