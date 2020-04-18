package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.udec.entity.ConsultDetail;

@Repository
public interface IConsultDetailRepository extends JpaRepository<ConsultDetail, Integer>{

}
