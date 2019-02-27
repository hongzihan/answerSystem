package com.hzh.answer.domain;

public class Paper {
	private Integer pid;
	private String pname;
	private Integer sid;
	private Long scount;
	
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
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Long getScount() {
		return scount;
	}
	public void setScount(Long scount) {
		this.scount = scount;
	}
	@Override
	public String toString() {
		return "Paper [pid=" + pid + ", pname=" + pname + ", sid=" + sid + ", scount=" + scount + "]";
	}
	
	
}
