package com.hzh.answer.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.PageBean;

public interface SubjectService {

	PageBean<Subject> findAllByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Integer saveSubject(Subject subject);

	Subject findOneBySid(Integer sid);

	Integer editSubject(Subject subject);

}
