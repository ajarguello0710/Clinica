package edu.udec.service.interfaces;

import edu.udec.dto.ExamDto;

public abstract interface IExamService extends ICrud<ExamDto, Integer> {

	public ExamDto getExamConsult(Integer idConsult);
}
