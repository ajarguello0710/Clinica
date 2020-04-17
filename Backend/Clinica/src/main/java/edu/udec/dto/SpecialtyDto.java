package edu.udec.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.udec.entity.Doctor;

public class SpecialtyDto {

	private Integer id;
	
	@NotNull(message = "Nombre especialidad obligatorio.")
	@Size(min = 5, max = 300, message = "Nombre no puede tener menos de 5 carácteres y más de 300.")
	private String name;
	
	private List<Doctor> doctors;

	public SpecialtyDto() {
		super();
	}

	public SpecialtyDto(Integer id,
			@NotNull(message = "Nombre especialidad obligatorio.") @Size(min = 5, max = 300, message = "Nombre no puede tener menos de 5 carácteres y más de 300.") String name,
			List<Doctor> doctors) {
		super();
		this.id = id;
		this.name = name;
		this.doctors = doctors;
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

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
}
