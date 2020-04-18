package edu.udec.service.interfaces;

import edu.udec.dto.PatientDto;

public abstract interface IPatientService extends ICrud<PatientDto, Integer>{

	public PatientDto disableState(Integer id); 
	
}
