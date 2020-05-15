package edu.udec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.udec.dto.ConsultDto;
import edu.udec.dto.ConsultListDto;
import edu.udec.entity.Consult;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
import edu.udec.repository.IConsultRepository;
import edu.udec.service.interfaces.IConsultService;

@Service("Consult")
public class ConsultServiceI implements IConsultService{
	
	@Autowired
	IConsultRepository repository;

	@Override
	public List<ConsultDto> get() {
		return covertListEntity(repository.findAll());
	}

	@Override
	public ConsultDto getId(Integer id) {
		return convertEntity(repository.findById(id).orElseThrow(() -> new NotFoundModelException("Consulta no encontrada.")));
	}

	@Override
	public ConsultDto save(ConsultDto objectSave) {
		if (objectSave.getId() == null) {
			ModelMapper mapper = new ModelMapper();
			objectSave.setDate(LocalDate.now());
			return convertEntity(repository.save(mapper.map(objectSave, Consult.class)));
		} else {
			throw new FilterValidationException("Id no es requerido.");
		}
	}

	@Override
	public ConsultDto edit(ConsultDto objectEdit) {
		if (objectEdit.getId() == null) {
			throw new ArgumentRequiredException("Id es requerido.");
		} 
		Consult consult = repository.findById(objectEdit.getId()).orElseThrow(
				() -> new NotFoundModelException("Consulta no encontrada."));
		if (objectEdit.getName() != consult.getName()) {
			consult.setName(objectEdit.getName());
		}
		if (objectEdit.getDate() != consult.getDate()) {
			consult.setDate(objectEdit.getDate());
		}
		return convertEntity(repository.save(consult));
	}

	@Override
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new NotFoundModelException("Consulta no encontrada.");
		} else {
			repository.deleteById(id);
		}
	}

	public List<ConsultDto> covertListEntity(List<Consult> consults) {
		ModelMapper mapper = new ModelMapper();
		List<ConsultDto> consultDtos = new ArrayList<ConsultDto>();
		for (Consult consult : consults) {
			consultDtos.add(mapper.map(consult, ConsultDto.class));
		}
		return consultDtos;
	}
	
	public List<ConsultListDto> covertListEntityList(List<Consult> consults) {
		ModelMapper mapper = new ModelMapper();
		List<ConsultListDto> consultDtos = new ArrayList<ConsultListDto>();
		for (Consult consult : consults) {
			consultDtos.add(mapper.map(consult, ConsultListDto.class));
		}
		return consultDtos;
	}
	
	public ConsultDto convertEntity(Consult consult) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(consult, ConsultDto.class);
	}

	@Override
	public List<ConsultListDto> getConsults(boolean detail) {
		List<Consult> consults = repository.getConsults();
		if (!detail) {
			for (Consult consult : consults) {
				consult.setConsultDetails(new ArrayList<>());
			}
		}
		return covertListEntityList(consults);
	}


}
