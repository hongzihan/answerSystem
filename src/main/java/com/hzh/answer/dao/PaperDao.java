package com.hzh.answer.dao;

import java.util.List;

import com.hzh.answer.dao.BaseDao;
import com.hzh.answer.domain.Paper;

public interface PaperDao extends BaseDao<Paper> {
	List<Paper> findAllWithItemCount();
}
