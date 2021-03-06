package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "consult_exam")
@IdClass(ConsultExamPK.class)
public class ConsultExam {
	
	@Id
	private Consult consult;
	
	@Id
	private Exam exam;
	
	@Column(name = "information", nullable = false, length = 50)
	@NotNull(message = "Información es obligatorio")
	@Size(min = 5, max = 300, message = "Información no puede tener menos de 5 carácteres y más de 300.")
	private String information;

	public ConsultExam() {
		super();
	}

	public ConsultExam(Consult consult, Exam exam, String information) {
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
