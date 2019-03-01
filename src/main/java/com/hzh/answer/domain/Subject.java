package com.hzh.answer.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 61780
 *
 */
public class Subject {
	private Integer sid;
	private Integer randomid;
	private String scontent;
	private String sa;
	private String sb;
	private String sc;
	private String sd;
	private String skey;
	private Integer sstate;
	private String studentkey;
	
	// 与Paper建立多对一关系
	private Set<Paper> papers = new HashSet<Paper>();
	
	
	
	public Set<Paper> getPapers() {
		return papers;
	}
	public void setPapers(Set<Paper> papers) {
		this.papers = papers;
	}
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getRandomid() {
		return randomid;
	}
	public void setRandomid(Integer randomid) {
		this.randomid = randomid;
	}
	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
	public String getSa() {
		return sa;
	}
	public void setSa(String sa) {
		this.sa = sa;
	}
	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = sb;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public Integer getSstate() {
		return sstate;
	}
	public void setSstate(Integer sstate) {
		this.sstate = sstate;
	}
	public String getStudentkey() {
		return studentkey;
	}
	public void setStudentkey(String studentkey) {
		this.studentkey = studentkey;
	}
	
	
}
