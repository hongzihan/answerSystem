package com.hzh.answer.domain.util;

/**
 * 用于存放错题信息的实体类
 * @author ken
 *
 */
public class StudentErrorSubject {
	private Integer sid;
	private String studentkey;
	private String scontent;
	private String sa;
	private String sb;
	private String sc;
	private String sd;
	private String skey;
	private String pname;
	private Integer userid;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getStudentkey() {
		return studentkey;
	}
	public void setStudentkey(String studentkey) {
		this.studentkey = studentkey;
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
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "StudentErrorSubject [sid=" + sid + ", studentkey=" + studentkey + ", scontent=" + scontent + ", sa="
				+ sa + ", sb=" + sb + ", sc=" + sc + ", sd=" + sd + ", skey=" + skey + ", pname=" + pname + ", userid="
				+ userid + "]";
	}
	
	
	
}
