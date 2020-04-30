package edu.udec.service.interfaces;

import java.util.List;

import edu.udec.dto.ConsultExamDto;
import edu.udec.dto.ConsultExamReportDto;

public interface IConsultExamService {
	
	public List<ConsultExamDto> get();
	
	public ConsultExamReportDto getId(Integer idConsult);
	
	public void save(ConsultExamDto objectSave);

}
