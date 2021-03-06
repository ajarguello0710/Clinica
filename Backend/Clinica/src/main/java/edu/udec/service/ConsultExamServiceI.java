package edu.udec.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.udec.dto.ConsultDto;
import edu.udec.dto.ConsultExamDto;
import edu.udec.dto.ConsultExamReportDto;
import edu.udec.dto.ExamReportDto;
import edu.udec.entity.Consult;
import edu.udec.entity.ConsultExam;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
import edu.udec.repository.IConsultExamRepository;
import edu.udec.repository.IConsultRepository;
import edu.udec.service.interfaces.IConsultExamService;

@Service("ConsultExam")
public class ConsultExamServiceI implements IConsultExamService {
	
	@Autowired
	private IConsultExamRepository repository;
	
	@Autowired
	private IConsultRepository repositoryConsulta;
	
	@Override
	public List<ConsultExamDto> get() {
		return covertListEntity(repository.findAll());
	}
	
	@Override
	public ConsultExamReportDto getId(Integer idConsult) {
		ConsultExamReportDto consultExamReportDto = new ConsultExamReportDto();
		Consult consultExams = repositoryConsulta.findById(idConsult).orElseThrow(
				() -> new NotFoundModelException("Consulta no encontrada."));
		
		if (consultExams != null) {
			ModelMapper mapper = new ModelMapper();
			ConsultDto consultDto = mapper.map(consultExams, ConsultDto.class);
			consultDto.setDoctor(null);
//			consultDto.setConsultDetails(null);
			consultDto.setPatient(null);
			consultExamReportDto.setConsultDto(consultDto);
			
			List<ExamReportDto> examReportDtos = repository.getId(idConsult);
			
			consultExamReportDto.setExamReportDtos(examReportDtos);
		}
		
		return consultExamReportDto;
	}
	
	@Override
	public void save(ConsultExamDto objectSave) {
		if (objectSave.getExam() != null && objectSave.getConsult() != null) {
			if(objectSave.getExam().getId() != null && objectSave.getConsult().getId() != null) {
				repository.save(objectSave.getConsult().getId(), objectSave.getExam().getId(), objectSave.getInformation());
			} else {
				throw new FilterValidationException("Id Consulta y Id Exámen son requeridos");
			}
		} else {
			throw new FilterValidationException("Consulta y Exámen son requeridos");
		}
		
	}
	
	public List<ConsultExamDto> covertListEntity(List<ConsultExam> consultExams) {
		ModelMapper mapper = new ModelMapper();
		List<ConsultExamDto> consultExamDtos = new ArrayList<ConsultExamDto>();
		for (ConsultExam consultExam : consultExams) {
			consultExam.getConsult().setConsultDetails(null);
			consultExamDtos.add(mapper.map(consultExam, ConsultExamDto.class));
		}
		return consultExamDtos;
	}
	
	public ConsultExamDto convertEntity(List<ConsultExam> list) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(list, ConsultExamDto.class);
	}
}
