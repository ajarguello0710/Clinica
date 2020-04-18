package edu.udec.dto;


import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import edu.udec.entity.Address;
import edu.udec.entity.Consult;
import edu.udec.entity.Specialty;

public class DoctorDto {

	private Integer id;
	
	@NotNull(message = "Nombre médico es obligatorio.")
	@Size(min = 3, max = 50, message = "El nombre del médico no puede tener menos de 3 carácteres y más de 50.")
	private String name;
	
	@NotNull(message = "Apellido médico es obligatorio.")
	@Size(min = 3, max = 50, message = "El apellido del médico no puede tener menos de 3 carácteres y más de 50.")
	private String lastName;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Fecha de nacimiento es obligatorio.")
	private LocalDate dateBirth;
	
	private Address address;
	
	@NotNull(message = "Estado doctor obligatorio.")
	private Boolean state;
	
	private List<Specialty> specialties;
	
	private List<Consult> consults;

	public DoctorDto() {
		super();
	}

	public DoctorDto(Integer id,
			@NotNull(message = "Nombre médico es obligatorio.") @Size(min = 3, max = 50, message = "El nombre del médico no puede tener menos de 3 carácteres y más de 50.") String name,
			@NotNull(message = "Apellido médico es obligatorio.") @Size(min = 3, max = 50, message = "El apellido del médico no puede tener menos de 3 carácteres y más de 50.") String lastName,
			@Past @NotNull(message = "Fecha de nacimiento es obligatorio.") LocalDate dateBirth, Address address,
			@NotNull(message = "Estado doctor obligatorio.") Boolean state, List<Specialty> specialties,
			List<Consult> consults) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.address = address;
		this.state = state;
		this.specialties = specialties;
		this.consults = consults;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}

	public List<Consult> getConsults() {
		return consults;
	}

	public void setConsults(List<Consult> consults) {
		this.consults = consults;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}
