package com.hzh.answer.service.impl;

import com.hzh.answer.dao.SysFunctionDao;
import com.hzh.answer.service.SysFunctionService;

/**
 * 系统应用Service层实现类
 * @author ken
 *
 */
public class SysFunctionServiceImpl implements SysFunctionService {
	private SysFunctionDao sysFunctionDao;

	public SysFunctionDao getSysFunctionDao() {
		return sysFunctionDao;
	}

	public void setSysFunctionDao(SysFunctionDao sysFunctionDao) {
		this.sysFunctionDao = sysFunctionDao;
	}
	
	
}
