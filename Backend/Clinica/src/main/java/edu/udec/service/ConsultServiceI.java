package edu.udec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.udec.dto.ConsultDto;
import edu.udec.dto.ConsultListDto;
import edu.udec.dto.ExamDto;
import edu.udec.dto.FullConsult;
import edu.udec.entity.Consult;
import edu.udec.entity.ConsultDetail;
import edu.udec.entity.ConsultExam;
import edu.udec.entity.Doctor;
import edu.udec.entity.Exam;
import edu.udec.entity.Patient;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
import edu.udec.repository.IConsultDetailRepository;
import edu.udec.repository.IConsultExamRepository;
import edu.udec.repository.IConsultRepository;
import edu.udec.repository.IExamRepository;
import edu.udec.service.interfaces.IConsultService;

@Service("Consult")
public class ConsultServiceI implements IConsultService {

	@Autowired
	IConsultRepository repositoryConsult;
	
	@Autowired
	IExamRepository repositoryExam;
	
	@Autowired
	IConsultDetailRepository repositoryConsultDetail;
	
	@Autowired
	private IConsultExamRepository repositoryConsultExam;

	@Override
	public List<ConsultDto> get() {
		return covertListEntity(repositoryConsult.findAll());
	}

	@Override
	public ConsultDto getId(Integer id) {
		return convertEntity(
				repositoryConsult.findById(id).orElseThrow(() -> new NotFoundModelException("Consulta no encontrada.")));
	}

	@Override
	public ConsultDto save(ConsultDto objectSave) {
		if (objectSave.getId() == null) {
			ModelMapper mapper = new ModelMapper();
			objectSave.setDate(LocalDate.now());
			return convertEntity(repositoryConsult.save(mapper.map(objectSave, Consult.class)));
		} else {
			throw new FilterValidationException("Id no es requerido.");
		}
	}

	@Override
	public ConsultDto edit(ConsultDto objectEdit) {
		if (objectEdit.getId() == null) {
			throw new ArgumentRequiredException("Id es requerido.");
		}
		Consult consult = repositoryConsult.findById(objectEdit.getId())
				.orElseThrow(() -> new NotFoundModelException("Consulta no encontrada."));
		if (objectEdit.getName() != consult.getName()) {
			consult.setName(objectEdit.getName());
		}
		if (objectEdit.getDate() != null) {
			if (objectEdit.getDate() != (consult.getDate())) {
				consult.setDate(objectEdit.getDate());
			}
		}
		if (objectEdit.getDoctor() != consult.getDoctor()) {
			consult.setDoctor(objectEdit.getDoctor());
		}
		if (objectEdit.getPatient() != consult.getPatient()) {
			consult.setPatient(objectEdit.getPatient() );
		}
		return convertEntity(repositoryConsult.save(consult));
	}

	@Override
	public void delete(Integer id) {
		if (!repositoryConsult.existsById(id)) {
			throw new NotFoundModelException("Consulta no encontrada.");
		} else {
			repositoryConsult.deleteById(id);
		}
	}
	
	@Transactional
	@Override
	public FullConsult SaveFullConsult(FullConsult fullConsult) {
		if (fullConsult.getDoctor() == null) {
			throw new ArgumentRequiredException("Doctor es requerido"); 
		}
		if (fullConsult.getPatient() == null) {
			throw new ArgumentRequiredException("Paciente es requerido"); 
		}
		ModelMapper mapper = new ModelMapper();
		Consult consult = new Consult(fullConsult.getName(), fullConsult.getDate(), new Doctor(fullConsult.getDoctor()), new Patient(fullConsult.getPatient()));
		repositoryConsult.save(consult);
		for (ConsultDetail consultDetail: fullConsult.getConsultDetailDtos()) {
			consultDetail.setConsult(consult);
			repositoryConsultDetail.save(consultDetail);
		}
		for (Exam exam: fullConsult.getExams()) {
			exam = repositoryExam.save(mapper.map(exam, Exam.class));

			repositoryConsultExam.save(consult.getId(), exam.getId(), exam.getDescription());
		}
		return fullConsult;
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
		List<ConsultListDto> consultsListDtos = covertListEntityList(repositoryConsult.getConsults());
		if (!detail) {
			for (ConsultListDto consultListDto : consultsListDtos) {
				consultListDto.setConsultDetails(new ArrayList<>());
				consultListDto.getDoctor().setAddress(null);
				consultListDto.getDoctor().setConsults(new ArrayList<>());
				consultListDto.getDoctor().setSpecialties(new ArrayList<>());
			}
		} else {
			for (ConsultListDto consultListDto : consultsListDtos) {
				consultListDto.getDoctor().setAddress(null);
				consultListDto.getDoctor().setConsults(new ArrayList<>());
				consultListDto.getDoctor().setSpecialties(new ArrayList<>());
			}
		}
		return consultsListDtos;
	}

}
