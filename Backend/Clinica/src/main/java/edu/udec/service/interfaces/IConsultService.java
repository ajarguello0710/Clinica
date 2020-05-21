package edu.udec.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.udec.dto.ConsultDto;
import edu.udec.dto.ConsultListDto;
import edu.udec.dto.FullConsult;
import edu.udec.entity.Consult;

public abstract interface IConsultService extends ICrud<ConsultDto, Integer>{
	
	public List<ConsultListDto> getConsults(boolean detail);
	
	public Page<ConsultListDto> getConsultsPageable(boolean detail, Pageable pageable);
	
	public Page<Consult> getConsultsForDoctor(String name, Pageable pageable);

	public FullConsult SaveFullConsult(FullConsult fullConsult);
}
