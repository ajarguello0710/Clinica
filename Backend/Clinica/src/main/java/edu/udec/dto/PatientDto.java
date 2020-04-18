package edu.udec.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import edu.udec.entity.Address;
import edu.udec.entity.Consult;

public class PatientDto {

	private Integer id;
	
	@Size(min = 4, max = 50, message = "El nombre del paciente no puede tener menos de 4 carácteres y más de 50.")
	@NotNull(message = "El nombre del paciente es obligatorio.")
	private String name;
	
	@Size(min = 4, max= 50, message = "El apellido del paciente no puede tener menos de 4 carácteres y más de 50.")
	private String lastName;
	
	private Address address;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Fecha de nacimiento es obligatorio.")
	private LocalDate dateBirth;
	
	@Email
	@NotNull(message = "El Email es obligatorio.")
	private String mail;
	
	@NotNull(message = "Estado paciente obligatorio.")
	private Boolean state;
	
	private List<Consult> consults;
	
	public PatientDto() {
		super();
	}

	public PatientDto(Integer id,
			@Size(min = 4, max = 50, message = "El nombre del paciente no puede tener menos de 4 carácteres y más de 50.") @NotNull(message = "El nombre del paciente es obligatorio.") String name,
			@Size(min = 4, max = 50, message = "El apellido del paciente no puede tener menos de 4 carácteres y más de 50.") String lastName,
			Address address, @Past @NotNull(message = "Fecha de nacimiento es obligatorio.") LocalDate dateBirth,
			@Email @NotNull(message = "El Email es obligatorio.") String mail,
			@NotNull(message = "Estado paciente obligatorio.") Boolean state, List<Consult> consults) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.dateBirth = dateBirth;
		this.mail = mail;
		this.state = state;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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