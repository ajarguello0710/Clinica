package edu.udec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Exam;

@Repository
public abstract interface IExamRepository extends JpaRepository<Exam, Integer> {
	
//	@Query("SELECT e FROM Exam JOIN ConsultExam ce ON ce.exam.id = e.consult.id WHERE ce.consult.id = :idConsult")
//	@Query("SELECT e FROM Exam e WHERE e.exam.")
//	public List<Exam> getExamConsult(@Param("idConsult") Integer idConsult);

}
