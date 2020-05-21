package edu.udec.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.udec.dto.PatientDto;
import edu.udec.entity.Patient;

public abstract interface IPatientService extends ICrud<PatientDto, Integer>{

	public PatientDto disableState(Integer id); 
	
	public Page<Patient> listPageable(Pageable pageable);
	
}
