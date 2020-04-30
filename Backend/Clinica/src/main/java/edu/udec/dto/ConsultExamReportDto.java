package edu.udec.dto;

import java.util.List;

public class ConsultExamReportDto {
	
	ConsultDto consultDto;
	
	List<ExamReportDto> examReportDtos;

	public ConsultDto getConsultDto() {
		return consultDto;
	}

	public void setConsultDto(ConsultDto consultDto) {
		this.consultDto = consultDto;
	}

	public List<ExamReportDto> getExamReportDtos() {
		return examReportDtos;
	}

	public void setExamReportDtos(List<ExamReportDto> examReportDtos) {
		this.examReportDtos = examReportDtos;
	}


}
