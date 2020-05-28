package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.udec.dto.PatientDto;
import edu.udec.entity.Patient;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
import edu.udec.repository.IPatientRepository;
import edu.udec.service.interfaces.IPatientService;

@Service("Patient")
public class PatientServiceI implements IPatientService {

	@Autowired
	IPatientRepository repository;
	
	@Override
	public List<PatientDto> get() {
		return covertListEntity(repository.findAll());
	}
	
	@Override
	public Page<Patient> listPageable(Pageable pageable) { 
		pageable.getSortOr(Sort.by("id").descending());
		Page<Patient> page = repository.findAll(pageable);
		for (Patient patient : page.getContent()) {
			patient.setConsults(new ArrayList<>());
		}
		return page;
	}

	@Override
	public PatientDto getId(Integer id) {
		return convertEntity(repository.findById(id).orElseThrow(() -> new NotFoundModelException("Paciente no encontrado.")));
	}

	@Override
	public PatientDto save(PatientDto objectSave) {
		if (objectSave.getId() == null) {
			ModelMapper mapper = new ModelMapper();
			return convertEntity(repository.save(mapper.map(objectSave, Patient.class)));
		} else {
			throw new FilterValidationException("Id no es requerido.");
		}
	}

	@Override
	public PatientDto edit(PatientDto objectEdit) {
		if (objectEdit.getId() == null) {
			throw new NotFoundModelException("Id es requerido.");
		}
		Patient patient = repository.findById(objectEdit.getId()).orElseThrow(
				() -> new NotFoundModelException("Paciente no encontrado."));	
		if (objectEdit.getName() != patient.getName()) {
			patient.setName(objectEdit.getName());
		}
		if (objectEdit.getLastName() != patient.getLastName()) {
			patient.setLastName(objectEdit.getLastName());
		}
		if (objectEdit.getDateBirth() != patient.getDateBirth()) {
			patient.setDateBirth(objectEdit.getDateBirth());
		}
		if (objectEdit.getMail() != patient.getMail()) {
			patient.setMail(objectEdit.getMail());
		}
		if (objectEdit.getState() != patient.getState()) {
			patient.setState(objectEdit.getState());
		}
		if (objectEdit.getAddress() != patient.getAddress()) {
			patient.setAddress(objectEdit.getAddress());
		}
		return convertEntity(repository.save(patient));
	}

	@Override
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new NotFoundModelException("Paciente no encontrado.");
		} else {
			repository.deleteById(id);
		}
	}

	@Override
	public PatientDto disableState(Integer id) {
		Patient patient = repository.findById(id).orElseThrow(
				() -> new NotFoundModelException("Paciente no encontrado."));
		if (patient.getState() == true) {
			patient.setState(false);
		} else {
			patient.setState(true);
		}
		return convertEntity(patient);
	}
	
	public List<PatientDto> covertListEntity(List<Patient> Patients) {
		ModelMapper mapper = new ModelMapper();
		List<PatientDto> PatientDtos = new ArrayList<PatientDto>();
		for (Patient Patient : Patients) {
			PatientDtos.add(mapper.map(Patient, PatientDto.class));
		}
		return PatientDtos;
	}
	
	public PatientDto convertEntity(Patient Patient) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(Patient, PatientDto.class);
	}

}
