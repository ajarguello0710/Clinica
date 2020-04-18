package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Specialty;

@Repository
public interface ISpecialtyRepository extends JpaRepository<Specialty, Integer> {

}
