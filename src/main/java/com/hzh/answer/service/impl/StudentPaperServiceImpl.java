package com.hzh.answer.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hzh.answer.dao.StudentPaperDao;
import com.hzh.answer.domain.PageBean;
import com.hzh.answer.domain.StudentPaper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.StudentErrorSubject;
import com.hzh.answer.service.StudentPaperService;

@Transactional
public class StudentPaperServiceImpl implements StudentPaperService {
	private StudentPaperDao studentPaperDao;

	public void setStudentPaperDao(StudentPaperDao studentPaperDao) {
		this.studentPaperDao = studentPaperDao;
	}

	/**
	 * 试卷题目具体信息保存方法
	 */
	@Override
	public Boolean save(StudentPaper studentPaper) {
		studentPaperDao.save(studentPaper);
		return true;
	}

	@Override
	public Long getRightCount(StudentPaper studentPaper) {
		Long rightCount = studentPaperDao.getRightCount(studentPaper);
		return rightCount;
	}

	@Override
	public PageBean<StudentErrorSubject> findErrorSubject(StudentPaper studentPaper, Integer currPage, Integer pageSize) {
		List<StudentErrorSubject> list = studentPaperDao.findErrorSubject(studentPaper);
		PageBean<StudentErrorSubject> pageBean = new PageBean<>();
		// 设置当前页面
		pageBean.setCurrPage(currPage);
		// 设置每页记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = list.size();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 每页数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<StudentErrorSubject> list2 = studentPaperDao.findErrorSubjectByPage(studentPaper,begin,pageSize);
		pageBean.setList(list2);
		return pageBean;
	}

	@Override
	public PageBean<StudentErrorSubject> findAllErrorSubject(StudentPaper studentPaper, Integer currPage,
			Integer pageSize) {
		List<StudentErrorSubject> list = studentPaperDao.findAllErrorSubject(studentPaper);
		PageBean<StudentErrorSubject> pageBean = new PageBean<>();
		// 设置当前页面
		pageBean.setCurrPage(currPage);
		// 设置每页记录数
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		Integer totalCount = list.size();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 每页数据的集合
		Integer begin = (currPage - 1) * pageSize;
		System.out.println("begin=" + begin);
		List<StudentErrorSubject> list2 = studentPaperDao.findAllErrorSubjectByPage(studentPaper,begin,pageSize);
		pageBean.setList(list2);
		return pageBean;
	}
	
	
}
