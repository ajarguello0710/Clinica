package edu.udec.service.interfaces;

import java.util.List;

import edu.udec.dto.ConsultDto;
import edu.udec.dto.ConsultListDto;
import edu.udec.dto.FullConsult;

public abstract interface IConsultService extends ICrud<ConsultDto, Integer>{
	
	public List<ConsultListDto> getConsults(boolean detail);
	
	public FullConsult SaveFullConsult(FullConsult fullConsult);
}
