package com.hzh.answer.dao;

import com.hzh.answer.domain.Subject;

public interface SubjectDao extends BaseDao<Subject> {

	Subject findOneByScontent(String scontent);

}
