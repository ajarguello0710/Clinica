package edu.udec.controller;

import java.util.ArrayList;
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

import edu.udec.dto.DoctorDto;
import edu.udec.service.interfaces.IDoctorService;

@PreAuthorize("hasAuthority('Administrador') or hasAuthority('Medico') or hasAuthority('Asistente')")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	@Qualifier("Doctor")
	IDoctorService service;
	
	@GetMapping("/get")
	public ResponseEntity<List<DoctorDto>> get() {
		List<DoctorDto> doctors = service.get();
		return new ResponseEntity<List<DoctorDto>>(doctors, HttpStatus.OK);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Integer>> listar() {
		List<Integer> doctors = new ArrayList<Integer>();
		doctors.add(1);
		doctors.add(2);
		doctors.add(3);
		doctors.add(4);
		doctors.add(5);
		doctors.add(6);
		return new ResponseEntity<List<Integer>>(doctors, HttpStatus.OK);
	}
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<DoctorDto> getId(@PathVariable Integer id) {
		DoctorDto doctor = service.getId(id);
		return new ResponseEntity<DoctorDto>(doctor, HttpStatus.OK);
	}
	
	@GetMapping("/getPageable")
	public ResponseEntity<Page<DoctorDto>> getPageable(Pageable pageable){
		Page<DoctorDto> page = service.listPagingated(pageable);
		return new ResponseEntity<Page<DoctorDto>>(page, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<DoctorDto> save(@Valid @RequestBody DoctorDto doctorDto) {
		DoctorDto doctor = service.save(doctorDto);
		return new ResponseEntity<DoctorDto>(doctor, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<DoctorDto> edit(@RequestBody DoctorDto doctorDto) {
		DoctorDto doctor = service.edit(doctorDto);
		return new ResponseEntity<DoctorDto>(doctor, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/disableState/{id}")
	public ResponseEntity<DoctorDto> disableState(@PathVariable Integer id) {
		DoctorDto doctor = service.disableState(id);
		return new ResponseEntity<DoctorDto>(doctor, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/assignSpecialty/{id}")
	public ResponseEntity<DoctorDto> assignSpecialty(@RequestBody List<Integer> idspecialties, @PathVariable Integer id) {
		DoctorDto doctorDto = service.assignSpecialty(idspecialties, id);
		return new ResponseEntity<DoctorDto>(doctorDto, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
