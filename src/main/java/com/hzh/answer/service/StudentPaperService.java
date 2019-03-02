package com.hzh.answer.service;

import java.util.List;

import com.hzh.answer.domain.StudentPaper;
import com.hzh.answer.domain.util.PageBean;
import com.hzh.answer.domain.util.StudentErrorSubject;

public interface StudentPaperService {

	Boolean save(StudentPaper studentPaper);

	Long getRightCount(StudentPaper studentPaper);

	PageBean<StudentErrorSubject> findErrorSubject(StudentPaper studentPaper, Integer currPage, Integer pageSize);

	PageBean<StudentErrorSubject> findAllErrorSubject(StudentPaper studentPaper, Integer currPage, Integer pageSize);

}
