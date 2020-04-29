package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.udec.dto.ConsultExamDto;
import edu.udec.entity.ConsultExam;
import edu.udec.exception.FilterValidationException;
import edu.udec.repository.IConsultExamRepository;
import edu.udec.service.interfaces.IConsultExamService;

@Service("ConsultExam")
public class ConsultExamI implements IConsultExamService {
	
	@Autowired
	private IConsultExamRepository repository;
	
	@Override
	public List<ConsultExamDto> get() {
		return covertListEntity(repository.findAll());
	}
	
	@Override
	public void save(ConsultExamDto objectSave) {
		if (objectSave.getExam() != null && objectSave.getConsult() != null) {
			if(objectSave.getExam().getId() != null && objectSave.getConsult().getId() != null) {
				repository.save(objectSave.getConsult().getId(), objectSave.getExam().getId(), objectSave.getInformation());
			} else {
				throw new FilterValidationException("Id Consulta y Id Exámen son requeridos");
			}
		}
		else {
			throw new FilterValidationException("Id Consulta y Id Exámen son requeridos");
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
