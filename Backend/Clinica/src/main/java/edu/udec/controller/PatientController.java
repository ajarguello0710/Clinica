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

import edu.udec.dto.PatientDto;
import edu.udec.entity.Patient;
import edu.udec.service.interfaces.IPatientService;

//@PreAuthorize("hasAuthority('Administrador') or hasAuthority('Paciente') or hasAuthority('Asistente') or hasAuthority('Medico')")
@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	@Qualifier("Patient")
	IPatientService service;
	
	@GetMapping("/get")
	public ResponseEntity<List<PatientDto>> get(){
		List<PatientDto> patients = service.get();
		return new ResponseEntity<List<PatientDto>>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/getPageable")
	public ResponseEntity<Page<Patient>> getPageable(Pageable pageable){
		Page<Patient> patients = service.listPageable(pageable);
		return new ResponseEntity<Page<Patient>>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<PatientDto> getId(@PathVariable Integer id){
		PatientDto patient = service.getId(id);
		return new ResponseEntity<PatientDto>(patient, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<PatientDto> save(@Valid @RequestBody PatientDto patientDto){
		PatientDto patient = service.save(patientDto);
		return new ResponseEntity<PatientDto>(patient, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<PatientDto> edit(@RequestBody PatientDto patientDto){
		PatientDto patient = service.edit(patientDto);
		return new ResponseEntity<PatientDto>(patient, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/disableState/{id}")
	public ResponseEntity<PatientDto> disableState(@PathVariable Integer id) {
		PatientDto patient = service.disableState(id);
		return new ResponseEntity<PatientDto>(patient, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
