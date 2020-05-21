package edu.udec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Consult;
import edu.udec.entity.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
	
	@Query("SELECT p.id, p.name, p.lastName FROM Patient p")
	public List<Consult> getPatients();
	
//	@Query(value = "SELECT * FROM patient", nativeQuery = true)
//	public Page<Patient> getPatientPage();

}
