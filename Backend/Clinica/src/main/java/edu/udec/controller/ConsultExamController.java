package edu.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.udec.dto.ConsultExamDto;
import edu.udec.service.interfaces.IConsultExamService;

@RestController
@RequestMapping("/consultExam")
public class ConsultExamController {
	
	@Autowired
	private IConsultExamService service;
	
	@GetMapping("/get")
	public ResponseEntity<List<ConsultExamDto>> get(){
		List<ConsultExamDto> consultExamDtos = service.get();
		return new ResponseEntity<List<ConsultExamDto>>(consultExamDtos, HttpStatus.OK);
	}
	

	
	@PostMapping("/save")
	public ResponseEntity<ConsultExamDto> save(@Valid @RequestBody ConsultExamDto consultExamDto){
		service.save(consultExamDto);
		return new ResponseEntity<ConsultExamDto>(consultExamDto, HttpStatus.CREATED);
	}
}
