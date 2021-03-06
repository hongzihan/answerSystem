package com.hzh.answer.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个用于记录题目回答情况的数据表
 * @author ken
 *
 */
public class StudentPaper {
	// StudentPaper的主键
	private Integer stupid; 
	private String spid;
	private Integer userid;
	private Integer sid;
	private String studentkey;
	private Integer studentstate;
	private String pname;
	private Integer rightcount;
	private Integer errorcount;
	
	private Set<Paper> papers = new HashSet<Paper>();
	
	
	
	public Set<Paper> getPapers() {
		return papers;
	}
	public void setPapers(Set<Paper> papers) {
		this.papers = papers;
	}
	public Integer getStupid() {
		return stupid;
	}
	public void setStupid(Integer stupid) {
		this.stupid = stupid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getStudentkey() {
		return studentkey;
	}
	public void setStudentkey(String studentkey) {
		this.studentkey = studentkey;
	}
	public Integer getStudentstate() {
		return studentstate;
	}
	public void setStudentstate(Integer studentstate) {
		this.studentstate = studentstate;
	}
	public void setRightcount(Integer rightcount) {
		this.rightcount = rightcount;
	}
	public Integer getRightcount() {
		return rightcount;
	}
	

	public Integer getErrorcount() {
		return errorcount;
	}
	public void setErrorcount(Integer errorcount) {
		this.errorcount = errorcount;
	}
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	@Override
	public String toString() {
		return "StudentPaper [stupid=" + stupid + ", spid=" + spid + ", userid=" + userid + ", sid=" + sid
				+ ", studentkey=" + studentkey + ", studentstate=" + studentstate + ", pname=" + pname + ", rightcount="
				+ rightcount + ", errorcount=" + errorcount + "]";
	}
	
}
