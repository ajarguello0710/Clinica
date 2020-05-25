package edu.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.udec.dto.ConsultDto;
import edu.udec.dto.ConsultListDto;
import edu.udec.dto.FullConsult;
import edu.udec.entity.Consult;
import edu.udec.service.interfaces.IConsultService;

@PreAuthorize("hasAuthority('Administrador')")
@RestController
@RequestMapping("/consult")
public class ConsultController {
	
	@Autowired
	@Qualifier("Consult")
	IConsultService service;

	@GetMapping("/get")
	public ResponseEntity<List<ConsultDto>> get(){
		List<ConsultDto> consultDtos = service.get();
		return new ResponseEntity<List<ConsultDto>>(consultDtos, HttpStatus.OK);
	}
	
	@GetMapping("/gets/{detail}")
	public ResponseEntity<List<ConsultListDto>> get(@PathVariable boolean detail){
		List<ConsultListDto> consultListDto = service.getConsults(detail);
		return new ResponseEntity<List<ConsultListDto>>(consultListDto, HttpStatus.OK);
	}
	
//	@PreAuthorize("hasAuthority('Medico')")
	@GetMapping("/getsPage/{detail}")
	public ResponseEntity<Page<ConsultListDto>> getPage(@PathVariable boolean detail, Pageable pageable){
		Page<ConsultListDto> consults = service.getConsultsPageable(detail, pageable);
		return new ResponseEntity<Page<ConsultListDto>>(consults, HttpStatus.OK);
	}
	
	@GetMapping("/getsPageName/{name}")
	public ResponseEntity<Page<Consult>> getPage(@PathVariable String name,Pageable pageable){
		Page<Consult> consults = service.getConsultsForDoctor(name, pageable);
		return new ResponseEntity<Page<Consult>>(consults, HttpStatus.OK);
	}
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<ConsultDto> getId(@PathVariable Integer id){
		ConsultDto consult = service.getId(id);
		return new ResponseEntity<ConsultDto>(consult, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ConsultDto> save(@Valid @RequestBody ConsultDto consultDto ){
		ConsultDto consult = service.save(consultDto );
		return new ResponseEntity<ConsultDto>(consult, HttpStatus.CREATED);
	}
	
	@PostMapping("/saveFull")
	public ResponseEntity<FullConsult> saveFull(@Valid @RequestBody FullConsult fullConsult ){
		FullConsult consult = service.SaveFullConsult(fullConsult);
		return new ResponseEntity<FullConsult>(consult, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<ConsultDto> edit(@RequestBody ConsultDto consultDto ){
		ConsultDto consult = service.edit(consultDto );
		return new ResponseEntity<ConsultDto>(consult, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
