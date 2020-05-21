package edu.udec.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.udec.dto.DoctorDto;

public abstract interface IDoctorService extends ICrud<DoctorDto, Integer>{

	public DoctorDto disableState(Integer id); 
	
	public DoctorDto assignSpecialty(List<Integer> idspecialties, Integer id);
	
	public Page<DoctorDto> listPagingated(Pageable pageable);
	
}
