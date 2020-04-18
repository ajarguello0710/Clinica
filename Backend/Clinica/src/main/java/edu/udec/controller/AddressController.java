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

import edu.udec.dto.AddressDto;
import edu.udec.service.interfaces.IAddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	@Qualifier("Address")
	IAddressService service;
	
	@GetMapping("/get")
	public ResponseEntity<List<AddressDto>> get(){
		List<AddressDto> addresses = service.get();
		return new ResponseEntity<List<AddressDto>>(addresses, HttpStatus.OK);
	}
	
	@GetMapping("/getId/{id}")
	public ResponseEntity<AddressDto> getId(@PathVariable Integer id){
		AddressDto address = service.getId(id);
		return new ResponseEntity<AddressDto>(address, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<AddressDto> save(@Valid @RequestBody AddressDto addressDto){
		AddressDto address = service.save(addressDto);
		return new ResponseEntity<AddressDto>(address, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<AddressDto> edit(@RequestBody AddressDto addressDto){
		AddressDto address = service.edit(addressDto);
		return new ResponseEntity<AddressDto>(address, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
