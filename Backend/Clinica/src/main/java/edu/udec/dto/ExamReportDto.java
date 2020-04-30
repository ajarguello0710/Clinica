package edu.udec.dto;

import edu.udec.entity.Exam;

public class ExamReportDto {

	private Exam exam;
	
	private String information;

	public ExamReportDto(Exam exam, String information) {
		super();
		this.exam = exam;
		this.information = information;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	
}
