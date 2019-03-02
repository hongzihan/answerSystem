package com.hzh.answer.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.SysUser;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.service.PaperService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 试卷的Action
 * 
 * @author ken
 *
 */
public class PaperAction extends ActionSupport implements ModelDriven<Paper> {

	private Paper paper = new Paper();

	private PaperService paperService;

	@Override
	public Paper getModel() {
		// TODO Auto-generated method stub
		return paper;
	}

	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
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
	 * 查询所有试卷粗略信息
	 */
	public String findAllPaper() {
		System.out.println(paper.getPname() + "****");
		// 创建离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Paper.class);
		//detachedCriteria.add(Restrictions.)
		if(paper.getPname()!=null && !"".equals(paper.getPname())) {
			detachedCriteria.add(Restrictions.like("pname", "%" + paper.getPname() + "%"));
		}
		PageBean<Paper> pageBean = paperService.findAllPaperWithPage(detachedCriteria,currPage,pageSize);
		// 为Paper中的Scount赋值，方便获取试题数量
		for (Paper paper : pageBean.getList()) {
			paper.setScount((long) paper.getSubjects().size());
		}
		ActionContext.getContext().getValueStack().push(pageBean);
		
		
		//List<Paper> list = paperService.findAllWithItemCount();
		//ActionContext.getContext().getValueStack().set("paperList", list);
		return "findAllPaperSuccess";
	}

	/**
	 * 查询单一试卷具体信息
	 * 
	 * @return
	 */
	public String searchPaper() {
		System.out.println(paper.getPid() + "pid");
		// 创建离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Paper.class);
		PageBean<Subject> pageBean = paperService.findAllSubjectWithPage(detachedCriteria,paper.getPid(),currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "searchSuccess";
	}
	
	/**
	 * 分页展示所有试卷
	 * @return
	 */
	public String list() {
		System.out.println(paper.getPname() + "****");
		// 创建离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Paper.class);
		//detachedCriteria.add(Restrictions.)
		if(paper.getPname()!=null && !"".equals(paper.getPname())) {
			detachedCriteria.add(Restrictions.like("pname", "%" + paper.getPname() + "%"));
		}
		PageBean<Paper> pageBean = paperService.findAllPaperWithPage(detachedCriteria,currPage,pageSize);
		// 为Paper中的Scount赋值，方便获取试题数量
		for (Paper paper : pageBean.getList()) {
			paper.setScount((long) paper.getSubjects().size());
		}
		ActionContext.getContext().getValueStack().push(pageBean);
		
		
		//List<Paper> list = paperService.findAllWithItemCount();
		//ActionContext.getContext().getValueStack().set("paperList", list);
		return "listSuccess";
	}
	
	/**
	 * viewPaper,列出所有题目
	 */
	public String viewPaper() {
		System.out.println(paper.getPid() + "pid");
		// 创建离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Paper.class);
		PageBean<Subject> pageBean = paperService.findAllSubjectWithPage(detachedCriteria,paper.getPid(),currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "viewPaperSuccess";
	}
	
	/**
	 * 跳转到试卷添加界面，add
	 */
	public String add() {
		return "addUI";
	}
	
	/**
	 * savePaper,新增试卷
	 */
	public String savePaper() {
		Integer saveStatus = paperService.savePaper(paper);
		if(saveStatus.equals(1)) {
			return "savePaperSuccess";
		} else {
			System.out.println("该试卷已经存在或者系统异常");
			return "addUI";
		}
	}
}
