package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class ConsultDetail {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "diagnosis")
	@NotNull(message = "Diagnostico es obligatoria.")
	@Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.")
	private String diagnosis;

	@Column(name = "treatment")
	@Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.")
	@NotNull(message = "Tratamiento es obligatorio.")
	private String treatment;
	
	public ConsultDetail() {
		super();
	}

	public ConsultDetail(Integer id,
			@NotNull(message = "Diagnostico es obligatoria.") @Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.") String diagnosis,
			@Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.") @NotNull(message = "Tratamiento es obligatorio.") String treatment,
			Consult query) {
		super();
		this.id = id;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
}
