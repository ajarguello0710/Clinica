package edu.udec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.udec.dto.ExamDto;
import edu.udec.service.interfaces.IExamService;

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
}
