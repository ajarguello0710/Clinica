package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.udec.dto.SpecialtyDto;
import edu.udec.entity.Specialty;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
import edu.udec.repository.ISpecialtyRepository;
import edu.udec.service.interfaces.ISpecialtyService;

@Service("Specialty")
public class SpecialtyServiceI implements ISpecialtyService{

	@Autowired
	ISpecialtyRepository repository;
	
	@Override
	public List<SpecialtyDto> get() {
		return covertListEntity(repository.findAll());
	}

	@Override
	public SpecialtyDto getId(Integer id) {
		return convertEntity(repository.findById(id).orElseThrow(() -> new NotFoundModelException("Especialización no encontrada.")));
	}

	@Override
	public SpecialtyDto save(SpecialtyDto objectSave) {
		if (objectSave.getId() == null) {
			ModelMapper mapper = new ModelMapper();
			return convertEntity(repository.save(mapper.map(objectSave, Specialty.class)));
		} else {
			throw new FilterValidationException("Id no es requerido.");
		}
	}

	@Override
	public SpecialtyDto edit(SpecialtyDto objectEdit) {
		if (objectEdit.getId() == null) {
			throw new NotFoundModelException("Id es requerido.");
		}
		Specialty specialty = repository.findById(objectEdit.getId()).orElseThrow(
				() -> new NotFoundModelException("Especialización no encontrada."));
		if (objectEdit.getName() != specialty.getName()) {
			specialty.setName(objectEdit.getName());
		}
		return convertEntity(repository.save(specialty));
	}

	@Override
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new NotFoundModelException("Especialización no encontrada.");
		} else {
			repository.deleteById(id);
		}		
	}

	public List<SpecialtyDto> covertListEntity(List<Specialty> Specialtys) {
		ModelMapper mapper = new ModelMapper();
		List<SpecialtyDto> SpecialtyDtos = new ArrayList<SpecialtyDto>();
		for (Specialty Specialty : Specialtys) {
			SpecialtyDtos.add(mapper.map(Specialty, SpecialtyDto.class));
		}
		return SpecialtyDtos;
	}
	
	public SpecialtyDto convertEntity(Specialty Specialty) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(Specialty, SpecialtyDto.class);
	}
}
