package com.hzh.answer.service.impl;

import java.util.List;

import com.hzh.answer.dao.PaperDao;
import com.hzh.answer.domain.Paper;
import com.hzh.answer.service.PaperService;

public class PaperServiceImpl implements PaperService {
	
	private PaperDao paperDao;
	
	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}

	@Override
	public List<Paper> findAllWithItemCount() {
		List<Paper> list = paperDao.findAllWithItemCount();
		return list;
	}

}
