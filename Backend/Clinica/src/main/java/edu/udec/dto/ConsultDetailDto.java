package edu.udec.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.udec.entity.Consult;

public class ConsultDetailDto {

	private Integer id;
	
	@NotNull(message = "Diagnostico es obligatoria.")
	@Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.")
	private String diagnosis;

	@Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.")
	@NotNull(message = "Tratamiento es obligatorio.")
	private String treatment;
	
	private Consult consult;
	
	public ConsultDetailDto() {
		super();
	}

	public ConsultDetailDto(Integer id,
			@NotNull(message = "Diagnostico es obligatoria.") @Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.") String diagnosis,
			@Size(min = 5, max = 300, message = "El diagnostico no puede tener menos de 5 carácteres y más de 300.") @NotNull(message = "Tratamiento es obligatorio.") String treatment,
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

	public Consult getConsult() {
		return consult;
	}

	public void setConsult(Consult consult) {
		this.consult = consult;
	}
}
