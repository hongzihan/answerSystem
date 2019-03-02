package com.hzh.answer.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hzh.answer.dao.PaperDao;
import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.service.PaperService;

@Transactional
public class PaperServiceImpl implements PaperService {
	
	private PaperDao paperDao;
	
	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}

	@Override
	public List<Subject> findByPname(String pname) {
		List<Subject> list = paperDao.findByPname(pname);
		return list;
	}

	@Override
	public PageBean<Paper> findAllPaperWithPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Paper> pageBean = new PageBean<Paper>();
		// 设置当前页数
		pageBean.setCurrPage(currPage);
		// 设置每页记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = paperDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 每页数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Paper> list = paperDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean<Subject> findAllSubjectWithPage(DetachedCriteria detachedCriteria,Integer pid, Integer currPage,
			Integer pageSize) {
		PageBean<Subject> pageBean = new PageBean<Subject>();
		// 设置当前页数
		pageBean.setCurrPage(currPage);
		// 设置每页记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer begin = (currPage - 1) * pageSize;
		Paper paper = paperDao.findById(pid);
		System.out.println(paper.getPname());
		Set<Subject> subjects = paper.getSubjects();
		Integer totalCount = subjects.size();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		//每页数据的集合
		//将Set集合转为List集合
		List<Subject> list2 = new ArrayList<Subject>(subjects);
		pageBean.setList(list2);
		return pageBean;
	}

	/**
	 * 新增试卷的业务
	 * 0,-1表示失败
	 * 1表示成功
	 */
	@Override
	public Integer savePaper(Paper paper) {
		Integer saveStatus = 0;
		Paper existPaper = paperDao.findOneByPname(paper.getPname());
		if(existPaper != null) {
			System.out.println("我错了");
			saveStatus = -1; 
		} else {
			System.out.println("我在");
			try {
				paperDao.save(paper);
				saveStatus = 1;
			} catch (Exception e) {
				e.printStackTrace();
				saveStatus = -1;
			}
		}
		return saveStatus;
	}

}
