package edu.udec.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.udec.entity.Consult;
import edu.udec.entity.Exam;

public class ConsultExamDto {
	
	private Consult consult;
	
	private Exam exam;
	
	@Column(name = "information", nullable = false, length = 50)
	@NotNull(message = "Información es obligatorio")
	@Size(min = 5, max = 300, message = "Información no puede tener menos de 5 carácteres y más de 300.")
	private String information;

	public ConsultExamDto() {
		super();
	}

	public ConsultExamDto(Exam exam,
			@NotNull(message = "Información es obligatorio") @Size(min = 5, max = 300, message = "Información no puede tener menos de 5 carácteres y más de 300.") String information) {
		super();
		this.exam = exam;
		this.information = information;
	}



	public ConsultExamDto(Consult consult, Exam exam,
			@NotNull(message = "Información es obligatorio") @Size(min = 5, max = 300, message = "Información no puede tener menos de 5 carácteres y más de 300.") String information) {
		super();
		this.consult = consult;
		this.exam = exam;
		this.information = information;
	}

	public Consult getConsult() {
		return consult;
	}

	public void setConsult(Consult consult) {
		this.consult = consult;
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
