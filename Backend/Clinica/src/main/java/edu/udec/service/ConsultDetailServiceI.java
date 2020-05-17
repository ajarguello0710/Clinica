package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.udec.dto.ConsultDetailDto;
import edu.udec.entity.ConsultDetail;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.FilterValidationException;
import edu.udec.exception.NotFoundModelException;
import edu.udec.repository.IConsultDetailRepository;
import edu.udec.service.interfaces.IConsultDetailService;

@Service("ConsultDetail")
public class ConsultDetailServiceI implements IConsultDetailService {

	@Autowired
	IConsultDetailRepository repository;
	
	@Override
	public List<ConsultDetailDto> get() {
		return covertListEntity(repository.findAll());
	}

	@Override
	public ConsultDetailDto getId(Integer id) {
		return convertEntity(repository.findById(id).orElseThrow(() -> new NotFoundModelException("Detalle consulta no encontrado.")));
	}

	@Override
	public ConsultDetailDto save(ConsultDetailDto objectSave) {
		if (objectSave.getId() == null) {
			ModelMapper mapper = new ModelMapper();
			return convertEntity(repository.save(mapper.map(objectSave, ConsultDetail.class)));
		} else {
			throw new FilterValidationException("Id no es requerido.");
		}
	}

	@Override
	public ConsultDetailDto edit(ConsultDetailDto objectEdit) {
		if (objectEdit.getId() == null) {
			throw new ArgumentRequiredException("Id es requerido.");
		}
		ConsultDetail consultDetail = repository.findById(objectEdit.getId()).orElseThrow(
				() -> new NotFoundModelException("Detalle consulta no encontrado."));
		if (objectEdit.getDiagnosis() != consultDetail.getDiagnosis()) {
			consultDetail.setDiagnosis(objectEdit.getDiagnosis());
		}
		if (objectEdit.getTreatment() != consultDetail.getTreatment()) {
			consultDetail.setTreatment(objectEdit.getTreatment());
		}
		return convertEntity(repository.save(consultDetail));
	}

	@Override
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new NotFoundModelException("Detalle consulta no encontrado.");
		} else {
			repository.deleteById(id);
		}
	}
	
	public List<ConsultDetailDto> covertListEntity(List<ConsultDetail> consultDetails) {
		ModelMapper mapper = new ModelMapper();
		List<ConsultDetailDto> consultDetailDtos = new ArrayList<ConsultDetailDto>();
		for (ConsultDetail consultDetail : consultDetails) {
			consultDetail.getConsult().setConsultDetails(null);
			consultDetailDtos.add(mapper.map(consultDetail, ConsultDetailDto.class));
		}
		return consultDetailDtos;
	}
	
	public ConsultDetailDto convertEntity(ConsultDetail consultDetail) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(consultDetail, ConsultDetailDto.class);
	}
}
