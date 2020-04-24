package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "consult_exam")
@IdClass(ConsultExamPK.class)
public class ConsultExam {
	
	@Id
	private Consult consult;
	
	@Id
	private Exam exam;
	
	@Column(name = "information", nullable = false, length = 50)
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
