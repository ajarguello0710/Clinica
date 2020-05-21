package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.udec.dto.ConsultListDto;
import edu.udec.dto.DoctorDto;
import edu.udec.entity.Doctor;
import edu.udec.entity.Specialty;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
import edu.udec.repository.IDoctorRepository;
import edu.udec.repository.ISpecialtyRepository;
import edu.udec.service.interfaces.IDoctorService;

@Service("Doctor")
public class DoctorServiceI implements IDoctorService{

	@Autowired
	IDoctorRepository repository;
	
	@Autowired
	ISpecialtyRepository repositorySpecialty;
	
	@Override
	public List<DoctorDto> get() {
		return covertListEntity(repository.findAll());
	}

	@Override
	public DoctorDto getId(Integer id) {
		return convertEntity(repository.findById(id).orElseThrow(() -> new NotFoundModelException("Doctor no encontrado.")));
	}
	
	

	@Override
	public DoctorDto save(DoctorDto objectSave) {
		if (objectSave.getId() == null) {
			ModelMapper mapper = new ModelMapper();
			return convertEntity(repository.save(mapper.map(objectSave, Doctor.class)));
		} else {
			throw new FilterValidationException("Id no es requerido.");
		}
	}

	@Override
	public DoctorDto edit(DoctorDto objectEdit) {
		if (objectEdit.getId() == null) {
			throw new ArgumentRequiredException("Id es requerido.");
		}
		Doctor doctor = repository.findById(objectEdit.getId()).orElseThrow(
				() -> new NotFoundModelException("Doctor no encontrado."));
		if (objectEdit.getName() != doctor.getName()) {
			doctor.setName(objectEdit.getName());
		}
		if (objectEdit.getLastName() != doctor.getLastName()) {
			doctor.setLastName(objectEdit.getLastName());
		}
		if (objectEdit.getDateBirth() != doctor.getDateBirth()) {
			doctor.setDateBirth(objectEdit.getDateBirth());
		}
		return convertEntity(repository.save(doctor));
	}

	@Override
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new NotFoundModelException("Doctor no encontrado.");
		} else {
			repository.deleteById(id);
		}
	}

	@Override
	public DoctorDto disableState(Integer id) {
		Doctor doctor = repository.findById(id).orElseThrow(
				() -> new NotFoundModelException("Doctor no encontrado."));
		if (doctor.getState() == true) {
			doctor.setState(false);
		} else {
			doctor.setState(true);
		}
		return convertEntity(doctor);
	}
	
	@Override
	public DoctorDto assignSpecialty(List<Integer> idspecialties, Integer id) {
		Doctor doctor = repository.findById(id).orElseThrow(
				() -> new NotFoundModelException("Doctor no encontrado."));
		List<Specialty> specialties = new ArrayList<Specialty>();
		if (doctor.getSpecialties().size() > 0) {
			for (Specialty specialty : doctor.getSpecialties()) {
				specialties.add(repositorySpecialty.findById(specialty.getId()).orElseThrow(() -> new NotFoundModelException("Especialidad no encontrada.")));
			}
		}
		for (Integer idSpecialty : idspecialties) {
			specialties.add(repositorySpecialty.findById(idSpecialty).orElseThrow(() -> new NotFoundModelException("Especialidad no encontrada.")));
		}
		doctor.setSpecialties(specialties);
		return convertEntity(repository.save(doctor));
	}
	
	public List<DoctorDto> covertListEntity(List<Doctor> Doctors) {
		ModelMapper mapper = new ModelMapper();
		List<DoctorDto> DoctorDtos = new ArrayList<DoctorDto>();
		for (Doctor Doctor : Doctors) {
			DoctorDtos.add(mapper.map(Doctor, DoctorDto.class));
		}
		return DoctorDtos;
	}
	
	public DoctorDto convertEntity(Doctor Doctor) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(Doctor, DoctorDto.class);
	}

	@Override
	public Page<DoctorDto> listPagingated(Pageable pageable) {
		
		List<DoctorDto> consultPage = covertListEntity(repository.findAll());
		Page<DoctorDto> page = new PageImpl<DoctorDto>(consultPage);
		
		for (DoctorDto doctorDto : page.getContent()) {
			doctorDto.setConsults(new ArrayList<>());
		}
		
		return page;
	}
}
