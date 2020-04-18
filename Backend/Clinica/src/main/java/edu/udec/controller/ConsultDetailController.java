package edu.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.udec.dto.ConsultDetailDto;
import edu.udec.service.interfaces.IConsultDetailService;

@RestController
@RequestMapping("/consultDetail")
public class ConsultDetailController {
	
	@Autowired
	@Qualifier("ConsultDetail")
	IConsultDetailService service;
	
	@GetMapping("/get")
	public ResponseEntity<List<ConsultDetailDto>> get(){
		List<ConsultDetailDto> consultDetails = service.get();
		return new ResponseEntity<List<ConsultDetailDto>>(consultDetails, HttpStatus.OK);
	}
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<ConsultDetailDto> getId(@PathVariable Integer id){
		ConsultDetailDto consultDetail = service.getId(id);
		return new ResponseEntity<ConsultDetailDto>(consultDetail, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ConsultDetailDto> save(@Valid @RequestBody ConsultDetailDto consultDetailDto){
		ConsultDetailDto consultDetail = service.save(consultDetailDto);
		return new ResponseEntity<ConsultDetailDto>(consultDetail, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<ConsultDetailDto> edit(@RequestBody ConsultDetailDto consultDetailDto){
		ConsultDetailDto consultDetail = service.edit(consultDetailDto);
		return new ResponseEntity<ConsultDetailDto>(consultDetail, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
