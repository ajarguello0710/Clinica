package edu.udec.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import edu.udec.entity.ConsultDetail;
import edu.udec.entity.Exam;

public class FullConsult {

	@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.")
	@NotNull(message = "El nombre de la consulta es obligatorio.")
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private Integer doctor;
	
	private Integer patient;
	
	private List<ConsultDetail> consultDetailDtos;
	
	private List<Exam> exams;
	
	public FullConsult() {
		super();
	}

	public FullConsult(
			@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.") @NotNull(message = "El nombre de la consulta es obligatorio.") String name,
			LocalDate date, Integer doctor, Integer patient, List<ConsultDetail> consultDetailDtos,
			List<Exam> exams) {
		super();
		this.name = name;
		this.date = date;
		this.doctor = doctor;
		this.patient = patient;
		this.consultDetailDtos = consultDetailDtos;
		this.exams = exams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getDoctor() {
		return doctor;
	}

	public void setDoctor(Integer doctor) {
		this.doctor = doctor;
	}

	public Integer getPatient() {
		return patient;
	}

	public void setPatient(Integer patient) {
		this.patient = patient;
	}

	public List<ConsultDetail> getConsultDetailDtos() {
		return consultDetailDtos;
	}

	public void setConsultDetailDtos(List<ConsultDetail> consultDetailDtos) {
		this.consultDetailDtos = consultDetailDtos;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}	
}
