package com.hzh.answer.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.domain.PageBean;
import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.Subject;

public interface PaperService {

	// 根据试卷名称查询单一试卷信息
	List<Subject> findByPname(String pname);

	// 分页查询所有试卷信息
	PageBean<Paper> findAllPaperWithPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	PageBean<Subject> findAllSubjectWithPage(DetachedCriteria detachedCriteria, Integer pid, Integer currPage,
			Integer pageSize);
}
