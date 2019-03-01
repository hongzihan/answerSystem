package com.hzh.answer.domain;

import java.util.HashSet;
import java.util.Set;

public class Paper {
	private Integer pid;
	private String pname;
	private Long scount;
	
	private Set<Subject> subjects = new HashSet<Subject>();
	
	
	
	public Set<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Long getScount() {
		return scount;
	}
	public void setScount(Long scount) {
		this.scount = scount;
	}
	
	
}
