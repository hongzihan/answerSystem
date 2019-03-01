package com.hzh.answer.dao;

import java.util.List;

import com.hzh.answer.dao.BaseDao;
import com.hzh.answer.domain.Paper;
import com.hzh.answer.domain.Subject;

public interface PaperDao extends BaseDao<Paper> {

	List<Subject> findByPname(String pname);
}
