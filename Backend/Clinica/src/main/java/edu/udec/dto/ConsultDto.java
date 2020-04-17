package edu.udec.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import edu.udec.entity.ConsultDetail;

public class ConsultDto {

	private Integer id;
	
	@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.")
	@NotNull(message = "El nombre de la consulta es obligatorio.")
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Fecha de consulta es obligatorio.")
	private LocalDate date;
	
	private List<ConsultDetail> consultDetails;

	public ConsultDto() {
		super();
	}

	public ConsultDto(Integer id,
			@Size(min = 3, max = 50, message = "Nombre de la consulta no puede tener menos de 3 carácteres y más de 50.") @NotNull(message = "El nombre de la consulta es obligatorio.") String name,
			@NotNull(message = "Fecha de consulta es obligatorio.") LocalDate date, List<ConsultDetail> consultDetails) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.consultDetails = consultDetails;
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
}
