package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.udec.dto.AddressDto;
import edu.udec.entity.Address;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
import edu.udec.repository.IAddressRepository;
import edu.udec.service.interfaces.IAddressService;

@Service("Address")
public class AddressServiceI implements IAddressService{

	@Autowired
	IAddressRepository repository;

	@Override
	public List<AddressDto> get() {
		return covertListEntity(repository.findAll());
	}

	@Override
	public AddressDto getId(Integer id) {
		return convertEntity(repository.findById(id).orElseThrow(() -> new NotFoundModelException("Dirección no encontrada.")));
	}

	@Override
	public AddressDto save(AddressDto objectSave) {
		if (objectSave.getId() == null) {
			ModelMapper mapper = new ModelMapper();
			Address address = mapper.map(objectSave, Address.class);
			return convertEntity(repository.save(address));
		} else {
			throw new FilterValidationException("Id no es requerido.");
		}
	}

	@Override
	public AddressDto edit(AddressDto objectEdit) {
		if (objectEdit.getId() == null) {
			throw new ArgumentRequiredException("Id es requerido");
		}
		Address address = repository.findById(objectEdit.getId()).orElseThrow(() -> new NotFoundModelException("Dirección no encontrada."));
		if (objectEdit.getAddress() != address.getAddress()) {
			address.setAddress(objectEdit.getAddress());
		}
		if (objectEdit.getNeighborhood() != address.getNeighborhood()) {
			address.setNeighborhood(objectEdit.getNeighborhood());
		}
		return convertEntity(repository.save(address));
	}

	@Override
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new NotFoundModelException("Dirección no encontrada.");
		} else {
			repository.deleteById(id);
		}
	}
	
	public List<AddressDto> covertListEntity(List<Address> addresses) {
		ModelMapper mapper = new ModelMapper();
		List<AddressDto> addressDtos = new ArrayList<AddressDto>();
		for (Address address : addresses) {
			addressDtos.add(mapper.map(address, AddressDto.class));
		}
		return addressDtos;
	}
	
	public AddressDto convertEntity(Address address) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(address, AddressDto.class);
	}
}
