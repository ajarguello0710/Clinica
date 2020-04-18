package edu.udec.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import edu.udec.entity.ConsultDetail;
import edu.udec.entity.Doctor;
import edu.udec.entity.Patient;

public class ConsultDto {

	private Integer id;
	
	@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.")
	@NotNull(message = "El nombre de la consulta es obligatorio.")
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private List<ConsultDetail> consultDetails;
	
	private Doctor doctor;
	
	private Patient patient;

	public ConsultDto() {
		super();
	}

	public ConsultDto(Integer id,
			@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.") @NotNull(message = "El nombre de la consulta es obligatorio.") String name,
			LocalDate date, List<ConsultDetail> consultDetails, Doctor doctor, Patient patient) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.consultDetails = consultDetails;
		this.doctor = doctor;
		this.patient = patient;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<ConsultDetail> getConsultDetails() {
		return consultDetails;
	}

	public void setConsultDetails(List<ConsultDetail> consultDetails) {
		this.consultDetails = consultDetails;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
