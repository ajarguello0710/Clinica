package edu.udec.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Patient {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	@Size(min = 3, max = 50, message = "El nombre del paciente no puede tener menos de 3 carácteres y más de 50.")
	@NotNull(message = "El nombre del paciente es obligatorio.")
	private String name;
	
	@Column(name = "last_name", nullable = false)
	@Size(min = 4, max= 50, message = "El apellido del paciente no puede tener menos de 4 carácteres y más de 50.")
	private String lastName;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_address")
	private Address address;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_birth", nullable = false)
	@NotNull(message = "Fecha de nacimiento es obligatorio.")
	private LocalDate dateBirth;
	
	@Email
	@Column(name = "mail", nullable = false)
	@NotNull(message = "El Email es obligatorio.")
	private String mail;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_consult")
	private List<Consult> consults;
	
	public Patient() {
		super();
	}

	public Patient(Integer id,
			@Size(min = 4, max = 50, message = "El nombre del paciente no puede tener menos de 4 carácteres y más de 50.") @NotNull(message = "El nombre del paciente es obligatorio.") String name,
			@Size(min = 4, max = 50, message = "El apellido del paciente no puede tener menos de 4 carácteres y más de 50.") String lastName,
			Address address, @Past @NotNull(message = "Fecha de nacimiento es obligatorio.") LocalDate dateBirth,
			@Email @NotNull(message = "El Email es obligatorio.") String mail, List<Consult> consults) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.dateBirth = dateBirth;
		this.mail = mail;
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
}
