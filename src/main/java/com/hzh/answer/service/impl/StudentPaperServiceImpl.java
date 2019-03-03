package com.hzh.answer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.tagext.PageData;

import org.springframework.transaction.annotation.Transactional;

import com.hzh.answer.dao.PaperDao;
import com.hzh.answer.dao.StudentPaperDao;
import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.StudentPaper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.domain.util.StudentErrorSubject;
import com.hzh.answer.domain.util.StudentPaperDetail;
import com.hzh.answer.service.StudentPaperService;

@Transactional
public class StudentPaperServiceImpl implements StudentPaperService {
	private StudentPaperDao studentPaperDao;
	private PaperDao paperDao;

	public void setStudentPaperDao(StudentPaperDao studentPaperDao) {
		this.studentPaperDao = studentPaperDao;
	}

	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}



	/**
	 * 试卷题目具体信息保存方法,级联保存已完成的题目和试卷的信息
	 */
	@Override
	public Boolean save(StudentPaper studentPaper) {
		// 首先获取对应试卷
		String pname = studentPaper.getPname();
		System.out.println(pname);
		Paper paper = null;
		if(pname!=null) {
			paper = paperDao.findPaperByPname(pname);
		} else {
			return false;
		}
		// 将试卷加入到对应的studentPaper中
		if(paper!=null) {
			studentPaper.getPapers().add(paper);
		}
		// 保存studentPaper
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
	public PageBean<StudentPaperDetail> findAllErrorSubject(StudentPaper studentPaper, Integer currPage,
			Integer pageSize) {
		List<StudentErrorSubject> list = studentPaperDao.findAllErrorSubject(studentPaper);
		PageBean<StudentPaperDetail> pageBean = new PageBean<>();
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
		
		// 获取所有的试卷
		List<Paper> paperList = paperDao.findAll();
		
		// 建立试卷详情List
		List<StudentPaperDetail> paperDetailList = new ArrayList<>();
		// 查出里面的错题列表
		for (Paper paper : paperList) {
			List<StudentPaper> studentPaperList;
			if(paper!=null) {
				// 获取对应题目list
				Set<StudentPaper> studentPapers = paper.getStudentPapers();
				studentPaperList = new ArrayList<>(studentPapers);
				// 获取对题和错题数目
				Long rightCount = 0l;
				Long errorCount = 0l;
				// 将题目信息和试卷信息整合
				StudentPaperDetail studentPaperDetail = new StudentPaperDetail();
				for (StudentPaper studentPaper2 : studentPaperList) {
					if(studentPaper2.getStudentstate().equals(1)) {
						rightCount += 1;
					}
					if(studentPaper2.getStudentstate().equals(0)) {
						errorCount += 1;
					}
					// 设置试卷时间
					if(studentPaper.getUserid().equals(studentPaper2.getUserid())) {
						studentPaperDetail.setSpid(Long.parseLong(studentPaper2.getSpid()));
					}
				}
				
				if(studentPaperDetail.getSpid()!=null) {
					// 设置错题总数
					studentPaperDetail.setErrorcount(errorCount);
					// 设置试卷名字
					studentPaperDetail.setPname(paper.getPname());
					// 设置对题总数
					studentPaperDetail.setRightcount(rightCount);
					// 将建好的数据加入DetailList
					paperDetailList.add(studentPaperDetail);
				}
				
			}
		}
		
		// 存入list
		pageBean.setList(paperDetailList);
		return pageBean;
	}
	
	
}
