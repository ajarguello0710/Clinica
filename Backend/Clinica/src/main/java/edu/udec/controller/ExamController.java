package edu.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.udec.dto.ExamDto;
import edu.udec.service.interfaces.IExamService;

@PreAuthorize("hasAuthority('Administrador') or hasAuthority('Medico') or hasAuthority('Asistente')")
@RestController
@RequestMapping("/exam")
public class ExamController {

	@Autowired
//	@Qualifier("Exam")
	IExamService service;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<ExamDto>> getExamConsult(@PathVariable Integer id) {
		ExamDto examDtos = service.getExamConsult(id);
		return new ResponseEntity<List<ExamDto>>((List<ExamDto>) examDtos, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ExamDto> save(@Valid @RequestBody ExamDto objectSave) {
		ExamDto examDto = service.save(objectSave);
		return new ResponseEntity<ExamDto>(examDto, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<ExamDto> edit(@RequestBody ExamDto objEdit){
		ExamDto examDto = service.edit(objEdit);
		return new ResponseEntity<ExamDto>(examDto, HttpStatus.ACCEPTED);
	}
	
}
