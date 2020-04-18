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

import edu.udec.dto.SpecialtyDto;
import edu.udec.service.interfaces.ISpecialtyService;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {
	
	@Autowired
	@Qualifier("Specialty")
	ISpecialtyService service;
	
	@GetMapping("/get")
	public ResponseEntity<List<SpecialtyDto>> get(){
		List<SpecialtyDto> specialtys = service.get();
		return new ResponseEntity<List<SpecialtyDto>>(specialtys, HttpStatus.OK);
	}
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<SpecialtyDto> getId(@PathVariable Integer id){
		SpecialtyDto specialty = service.getId(id);
		return new ResponseEntity<SpecialtyDto>(specialty, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<SpecialtyDto> save(@Valid @RequestBody SpecialtyDto specialtyDto){
		SpecialtyDto specialty = service.save(specialtyDto);
		return new ResponseEntity<SpecialtyDto>(specialty, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<SpecialtyDto> edit(@RequestBody SpecialtyDto specialtyDto){
		SpecialtyDto specialty = service.edit(specialtyDto);
		return new ResponseEntity<SpecialtyDto>(specialty, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
