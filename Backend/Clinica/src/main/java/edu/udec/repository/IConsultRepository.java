package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Consult;

@Repository
public interface IConsultRepository extends JpaRepository<Consult, Integer> {

}
