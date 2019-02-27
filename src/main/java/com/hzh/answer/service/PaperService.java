package com.hzh.answer.service;

import java.util.List;

import com.hzh.answer.domain.Paper;

public interface PaperService {
	List<Paper> findAllWithItemCount(); // 查询所有试卷包括其包含的题目数
}
