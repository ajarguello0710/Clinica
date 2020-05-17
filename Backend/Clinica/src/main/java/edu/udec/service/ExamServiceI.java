package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.udec.dto.ExamDto;
import edu.udec.entity.Exam;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
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

	@Override
	public List<ExamDto> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamDto getId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamDto save(ExamDto objectSave) {
		if (objectSave.getId() == null) {
			ModelMapper mapper = new ModelMapper();
			return convertEntity(repository.save(mapper.map(objectSave, Exam.class)));
		} else {
			throw new FilterValidationException("Id no es requerido");
		}
	}

	@Override
	public ExamDto edit(ExamDto objectEdit) {
		if (objectEdit.getId() == null) {
			throw new NotFoundModelException("Id es requerido.");
		}
		Exam exam = repository.findById(objectEdit.getId()).orElseThrow(
				() -> new NotFoundModelException("Especialidad no encontrada."));
		if (objectEdit.getName() != exam.getName()) {
			exam.setName(objectEdit.getName());
		}
		if (objectEdit.getDescription() != exam.getDescription()) {
			exam.setDescription(objectEdit.getDescription());
		}
		return convertEntity(repository.save(exam));
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
