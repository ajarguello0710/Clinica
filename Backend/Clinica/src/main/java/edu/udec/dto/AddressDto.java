package edu.udec.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressDto {

	private Integer id;
	
	@NotNull(message = "Dirección es obligatoria.")
	@Size(min = 3, max = 50, message = "Dirección no puede tener menos de 3 carácteres y más de 50.")
	private String address;
	
	@NotNull(message = "Barrio es obligatorio.")
	@Size(min = 3, max = 50, message = "Barrio no puede tener menos de 3 carácteres y más de 50.")
	private String neighborhood;

	public AddressDto() {
		super();
	}

	public AddressDto(Integer id,
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
