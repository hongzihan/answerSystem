package com.hzh.answer.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.service.SubjectService;
import com.opensymphony.xwork2.ActionContext;
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
	
	// 分页参数
	private Integer currPage = 1;
	private Integer pageSize = 3;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 分页列出所有题目
	 * @return
	 */
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subject.class);
		if(subject.getScontent() != null && !"".equals(subject.getScontent())) {
			detachedCriteria.add(Restrictions.like("scontent", "%" + subject.getScontent() + "%"));
		}
		PageBean<Subject> pageBean = subjectService.findAllByPage(detachedCriteria,currPage,pageSize);
		
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "listSuccess";
	}
	
	/**
	 * 跳转到题目添加页面
	 */
	public String add() {
		return "addUI";
	}
	
	/**
	 * 新增题目
	 */
	public String addSubject() {
		Integer saveStatus = subjectService.saveSubject(subject);
		if(saveStatus.equals(1)) {
			return "addSubjectSuccess";
		} else {
			System.out.println("该题目已经存在或者系统异常");
			return "addUI";
		}
	}
	
	/**
	 * 跳转到题目编辑页面
	 * @return
	 */
	public String edit() {
		Subject subject2 = subjectService.findOneBySid(subject.getSid());
		ActionContext.getContext().getValueStack().push(subject2);
		return "editUI";
	}
	
	/**
	 * 编辑题目
	 */
	public String updateSubject() {
		Integer editStatus = subjectService.editSubject(subject);
		if(editStatus.equals(1)) {
			return "editSubjectSuccess";
		} else {
			return "editUI";
		}
	}
}
