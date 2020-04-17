package edu.udec.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Specialty {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@NotNull(message = "Nombre especialidad obligatorio.")
	@Size(min = 5, max = 300, message = "Nombre no puede tener menos de 5 carácteres y más de 300.")
	private String name;

	@ManyToMany(mappedBy = "specialties")
	private List<Doctor> doctors;
	
	public Specialty() {
		super();
	}

	public Specialty(Integer id,
			@NotNull(message = "Nombre especialidad obligatorio.") @Size(min = 5, max = 300, message = "Nombre no puede tener menos de 5 carácteres y más de 300.") String name) {
		super();
		this.id = id;
		this.name = name;
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
}
