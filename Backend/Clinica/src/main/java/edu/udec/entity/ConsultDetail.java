package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Size(min = 5, max = 300, message = "El tratamiento no puede tener menos de 5 carácteres y más de 300.")
	@NotNull(message = "Tratamiento es obligatorio.")
	private String treatment;
	
	@ManyToOne
	@JoinColumn(name = "fk_id_consult", nullable = false, foreignKey = @ForeignKey(name = "FK_consult_detail"))
	private Consult consult;
	
	public ConsultDetail() {
		super();
	}
	
	public ConsultDetail(Integer id,
			@NotNull(message = "Diagnostico es obligatoria.") @Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.") String diagnosis,
			@Size(min = 5, max = 300, message = "El tratamiento no puede tener menos de 5 carácteres y más de 300.") @NotNull(message = "Tratamiento es obligatorio.") String treatment,
			Consult consult) {
		super();
		this.id = id;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.consult = consult;
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

	@JsonIgnore
	public Consult getConsult() {
		return consult;
	}

	public void setConsult(Consult consult) {
		this.consult = consult;
	}
}
