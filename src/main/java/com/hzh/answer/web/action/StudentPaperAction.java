package com.hzh.answer.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.domain.PageBean;
import com.hzh.answer.domain.StudentPaper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.StudentErrorSubject;
import com.hzh.answer.service.StudentPaperService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.mail.iap.Response;

/**
 * 学生试卷题目具体的Action
 * @author ken
 *
 */
public class StudentPaperAction extends ActionSupport implements ModelDriven<StudentPaper> {

	private StudentPaper studentPaper = new StudentPaper();
	
	private StudentPaperService studentPaperService;
	
	public void setStudentPaperService(StudentPaperService studentPaperService) {
		this.studentPaperService = studentPaperService;
	}

	@Override
	public StudentPaper getModel() {
		return studentPaper;
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
	 * 处理交卷的信息
	 */
	public void answer() {
		StudentPaper studentPaper2 = new StudentPaper();
		studentPaper2 = studentPaper;
		Boolean saveSuccess = studentPaperService.save(studentPaper2);
		if(saveSuccess) {
			System.out.println("保存成功");
		} else {
			System.out.println("保存失败");
		}
	}

	/**
	 * 用于计算成绩
	 * @return
	 * @throws IOException 
	 */
	public String caculateScore() throws IOException {
		Long rightCount = studentPaperService.getRightCount(studentPaper);
		int score = rightCount.intValue();
		System.out.println("score=" + score);
		ServletActionContext.getRequest().setAttribute("score", score*2);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		response.setCharacterEncoding("GBK");	
		PrintWriter out = response.getWriter();
		out.println("您本次得分" + score * 2 + "分!");
		out.flush();
		out.close();
		System.out.println(ServletActionContext.getRequest().getContextPath() + "&&&");
		return NONE;
	}
	
	/**
	 * 用于获取成绩,跳转到错题页面
	 * @return
	 */
	public String errorSubjectPage() {
		
		
		// 获取pageBean
		PageBean<StudentErrorSubject> pageBean = studentPaperService.findErrorSubject(studentPaper,currPage,pageSize);
		// 将pageBean存入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "errorSubjectPageUI";
	}
	
	/**
	 * 跳转到当前用户的所有错题页面
	 * @return
	 */
	public String allErrorSubjectPage() {
		System.out.println(currPage + "currPage" + "PageSize" + pageSize);
		// 获取pageBean
		PageBean<StudentErrorSubject> pageBean = studentPaperService.findAllErrorSubject(studentPaper,currPage,pageSize);
		List<StudentErrorSubject> list = pageBean.getList();
		// 将pageBean存入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "allErrorSubjectPageUI";
	}
}
