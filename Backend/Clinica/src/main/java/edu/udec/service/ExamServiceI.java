package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.udec.dto.ExamDto;
import edu.udec.entity.Exam;
import edu.udec.repository.IExamRepository;
import edu.udec.service.interfaces.IExamService;

@Service
public class ExamServiceI implements IExamService {
	
	@Autowired
	private IExamRepository repository;

	@Override
	public ExamDto getExamConsult(Integer idConsult) {
//		List<ExamDto> examDtos = convertListEntity(repository.getExamConsult(idConsult));
//		return (ExamDto) examDtos;
		return null;
	}
	
	public List<ExamDto> convertListEntity(List<Exam> exams) {
		ModelMapper mapper = new ModelMapper();
		List<ExamDto> examDtos = new ArrayList<ExamDto>();
		for (Exam exam : exams) {
			examDtos.add(mapper.map(exam, ExamDto.class));
		}
		return examDtos;
	}
	
	public ExamDto convertEntity(Exam exam) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(exam, ExamDto.class);
	}

}
