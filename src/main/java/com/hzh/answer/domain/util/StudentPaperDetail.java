package com.hzh.answer.domain.util;

/**
 * 试卷详情页的辅助实例
 * @author ken
 *
 */
public class StudentPaperDetail {
	private String pname;
	private Long errorcount;
	private Long rightcount;
	private Long spid;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Long getErrorcount() {
		return errorcount;
	}
	public void setErrorcount(Long errorcount) {
		this.errorcount = errorcount;
	}
	public Long getRightcount() {
		return rightcount;
	}
	public void setRightcount(Long rightcount) {
		this.rightcount = rightcount;
	}
	public Long getSpid() {
		return spid;
	}
	public void setSpid(Long spid) {
		this.spid = spid;
	}
	
	
}
