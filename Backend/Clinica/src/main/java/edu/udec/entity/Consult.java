package edu.udec.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "consult")
public class Consult {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.")
	@NotNull(message = "El nombre de la consulta es obligatorio.")
	private String name;
	
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@OneToMany(mappedBy = "consult", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ConsultDetail> consultDetails;
	
	@ManyToOne
	@JoinColumn(name = "fk_id_doctor", nullable = false, foreignKey = @ForeignKey(name = "fk_doctor_consult"))
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "fk_id_patient", nullable = false, foreignKey = @ForeignKey(name = "fk_patient_consult"))
	private Patient patient;

	public Consult() {
		super();
	}

	public Consult(
			@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.") @NotNull(message = "El nombre de la consulta es obligatorio.") String name,
			LocalDate date, List<ConsultDetail> consultDetails, Doctor doctor, Patient patient) {
		super();
		this.name = name;
		this.date = date;
		this.consultDetails = consultDetails;
		this.doctor = doctor;
		this.patient = patient;
	}

	public Consult(
			@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.") @NotNull(message = "El nombre de la consulta es obligatorio.") String name,
			LocalDate date, Doctor doctor, Patient patient) {
		super();
		this.name = name;
		this.date = date;
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

	@JsonIgnore
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@JsonIgnore
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consult other = (Consult) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
