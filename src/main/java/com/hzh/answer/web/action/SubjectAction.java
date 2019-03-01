package com.hzh.answer.web.action;

import com.hzh.answer.domain.Subject;
import com.hzh.answer.service.SubjectService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SubjectAction extends ActionSupport implements ModelDriven<Subject> {

	private Subject subject = new Subject();
	
	private SubjectService subjectService;
	
	@Override
	public Subject getModel() {
		// TODO Auto-generated method stub
		return subject;
	}

	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	
}
