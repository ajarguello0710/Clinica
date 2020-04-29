package edu.udec.service.interfaces;

import java.util.List;

import edu.udec.dto.ConsultExamDto;

public interface IConsultExamService {
	
	public List<ConsultExamDto> get();
	
	public void save(ConsultExamDto objectSave);

}
