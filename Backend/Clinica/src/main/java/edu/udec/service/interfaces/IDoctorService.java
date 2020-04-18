package edu.udec.service.interfaces;

import java.util.List;

import edu.udec.dto.DoctorDto;

public abstract interface IDoctorService extends ICrud<DoctorDto, Integer>{

	public DoctorDto disableState(Integer id); 
	
	public DoctorDto assignSpecialty(List<Integer> idspecialties, Integer id);
	
}
