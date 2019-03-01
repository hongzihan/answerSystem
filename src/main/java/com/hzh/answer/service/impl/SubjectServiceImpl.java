package com.hzh.answer.service.impl;

import com.hzh.answer.dao.SubjectDao;
import com.hzh.answer.service.SubjectService;

public class SubjectServiceImpl implements SubjectService{
	private SubjectDao subjectDao;

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}
	
}
