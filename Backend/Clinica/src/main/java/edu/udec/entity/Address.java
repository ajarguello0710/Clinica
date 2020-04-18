package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class Address {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "address")
	@NotNull(message = "Dirección es obligatoria.")
	@Size(min = 3, max = 50, message = "Dirección no puede tener menos de 3 carácteres y más de 50.")
	private String address;
	
	@Column(name = "neighborhood")
	@NotNull(message = "Barrio es obligatorio.")
	@Size(min = 3, max = 50, message = "Barrio no puede tener menos de 3 carácteres y más de 50.")
	private String neighborhood;
	
	public Address() {
		super();
	}

	public Address(Integer id,
			@NotNull(message = "Dirección es obligatoria.") @Size(min = 3, max = 50, message = "Dirección no puede tener menos de 3 carácteres y más de 50.") String address,
			@NotNull(message = "Barrio es obligatorio.") @Size(min = 3, max = 50, message = "Barrio no puede tener menos de 3 carácteres y más de 50.") String neighborhood) {
		super();
		this.id = id;
		this.address = address;
		this.neighborhood = neighborhood;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
}
