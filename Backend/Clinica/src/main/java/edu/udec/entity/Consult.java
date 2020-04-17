package edu.udec.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
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
	@NotNull(message = "Fecha de consulta es obligatorio.")
	private LocalDate date;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_consult_details")
	private List<ConsultDetail> consultDetails;

	public Consult() {
		super();
	}

	public Consult(Integer id,
			@Size(min = 3, max = 50, message = "El nombre de la consulta no puede tener menos de 3 carácteres y más de 50.") @NotNull(message = "El nombre de la consulta es obligatorio.") String name,
			@NotNull(message = "Fecha de consulta es obligatorio.") LocalDate date,
			List<ConsultDetail> consultDetails) {
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
