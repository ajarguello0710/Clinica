package edu.udec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.udec.dto.ConsultDto;
import edu.udec.dto.ConsultListDto;
import edu.udec.dto.FullConsult;
import edu.udec.entity.Address;
import edu.udec.entity.Consult;
import edu.udec.entity.ConsultDetail;
import edu.udec.entity.Doctor;
import edu.udec.entity.Exam;
import edu.udec.entity.Patient;
import edu.udec.entity.Specialty;
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
		return convertEntity(repositoryConsult.findById(id)
				.orElseThrow(() -> new NotFoundModelException("Consulta no encontrada.")));
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
			consult.setPatient(objectEdit.getPatient());
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
		Consult consult = new Consult(fullConsult.getName(), fullConsult.getDate(), new Doctor(fullConsult.getDoctor()),
				new Patient(fullConsult.getPatient()));
		repositoryConsult.save(consult);
		for (ConsultDetail consultDetail : fullConsult.getConsultDetailDtos()) {
			consultDetail.setConsult(consult);
			repositoryConsultDetail.save(consultDetail);
		}
		for (Exam exam : fullConsult.getExams()) {
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

	public List<ConsultListDto> covertListEntityPage(Page<ConsultListDto> page) {
		ModelMapper mapper = new ModelMapper();
		List<ConsultListDto> consultDtos = new ArrayList<ConsultListDto>();
		for (ConsultListDto consult : page.getContent()) {
			consultDtos.add(mapper.map(consult, ConsultListDto.class));
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
		List<ConsultListDto> consultsListDtos = covertListEntityList(repositoryConsult.findAll());
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

	public Page<ConsultListDto> getConsultsPageable(boolean detail, Pageable pageable) {

		List<ConsultListDto> consultPage = covertListEntityList(repositoryConsult.findAll());
		Page<ConsultListDto> page = new PageImpl<ConsultListDto>(consultPage);
		
		if (!detail) {
			for (ConsultListDto consult : page.getContent()) {
				consult.setConsultDetails(new ArrayList<>());
				consult.getDoctor().setConsults(new ArrayList<>());
				consult.getDoctor().setSpecialties(new ArrayList<>());
				consult.getDoctor().setAddress(null);
			}
		} else {
			for (ConsultListDto consult : page.getContent()) {
				consult.getDoctor().setConsults(new ArrayList<>());
				consult.getDoctor().setSpecialties(new ArrayList<>());
				consult.getDoctor().setAddress(null);
			}
		}
		return page;
	}

	@Override
	public Page<Consult> getConsultsForDoctor(String nameDoctor, Pageable pageable) {
		
		Page<Consult> page = repositoryConsult.findByDoctor_NameIgnoreCaseContaining(nameDoctor, pageable);
		for (Consult consult : page.getContent()) {
			consult.setConsultDetails(new ArrayList<>());
		}
		return page;
	}


}
