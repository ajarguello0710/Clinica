package edu.udec.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	@NotNull(message = "Nombre médico es obligatorio.")
	@Size(min = 3, max = 50, message = "Nombre del médico no puede tener menos de 3 carácteres y más de 50.")
	private String name;
	
	@Column(name = "last_name", nullable = false)
	@NotNull(message = "Apellido médico es obligatorio.")
	@Size(min = 3, max = 50, message = "Apellido del médico no puede tener menos de 3 carácteres y más de 50.")
	private String lastName;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_birth", nullable = false)
	@NotNull(message = "Fecha de nacimiento es obligatorio.")
	private LocalDate dateBirth;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_address", referencedColumnName = "id", nullable = false)
	private Address address;
	
	@Column(name = "state", nullable = false)
	@NotNull(message = "Estado doctor obligatorio.")
	private Boolean state;
	
	@OneToMany(mappedBy = "doctor", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Consult> consults;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "doctor_specialty",
        joinColumns = @JoinColumn(name = "specialty_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
	private List<Specialty> specialties;

	public Doctor() {
		super();
	}

	public Doctor(Integer id) {
		super();
		this.id = id;
	}

	public Doctor(Integer id,
			@NotNull(message = "Nombre médico es obligatorio.") @Size(min = 3, max = 50, message = "Nombre del médico no puede tener menos de 3 carácteres y más de 50.") String name,
			@NotNull(message = "Apellido médico es obligatorio.") @Size(min = 3, max = 50, message = "Apellido del médico no puede tener menos de 3 carácteres y más de 50.") String lastName,
			@Past @NotNull(message = "Fecha de nacimiento es obligatorio.") LocalDate dateBirth, Address address,
			@NotNull(message = "Estado doctor obligatorio.") Boolean state, List<Consult> consults,
			List<Specialty> specialties) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.address = address;
		this.state = state;
		this.consults = consults;
		this.specialties = specialties;
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

	public List<Consult> getConsults() {
		return consults;
	}

	public void setConsults(List<Consult> consults) {
		this.consults = consults;
	}

	public List<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}
