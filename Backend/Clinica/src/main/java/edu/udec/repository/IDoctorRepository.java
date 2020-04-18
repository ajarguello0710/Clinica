package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Doctor;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Integer>{

}
