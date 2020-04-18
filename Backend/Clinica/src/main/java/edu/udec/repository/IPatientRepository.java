package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {

}
