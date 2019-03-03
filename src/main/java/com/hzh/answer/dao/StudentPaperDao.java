package com.hzh.answer.dao;

import java.util.List;

import com.hzh.answer.domain.StudentPaper;
import com.hzh.answer.domain.Subject;
import com.hzh.answer.domain.util.StudentErrorSubject;
import com.hzh.answer.domain.util.StudentPaperDetail;

public interface StudentPaperDao extends BaseDao<StudentPaper> {

	Long getRightCount(StudentPaper studentPaper);

	List<StudentErrorSubject> findErrorSubject(StudentPaper studentPaper);

	List<StudentErrorSubject> findErrorSubjectByPage(StudentPaper studentPaper, Integer begin, Integer pageSize);

	List<StudentErrorSubject> findAllErrorSubjectByPage(StudentPaper studentPaper, Integer begin, Integer pageSize);

	List<StudentErrorSubject> findAllErrorSubject(StudentPaper studentPaper);

}
