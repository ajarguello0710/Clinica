package edu.udec.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.udec.dto.ExamReportDto;
import edu.udec.entity.ConsultExam;

@Repository
public interface IConsultExamRepository extends JpaRepository<ConsultExam, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO consult_exam(pk_id_consult, pk_id_exam, information) "
			+ "VALUES(:idConsult, :idExam, :information)", nativeQuery = true)
	public void save(
			@Param("idConsult") Integer idConsult,
			@Param("idExam") Integer idExam,
			@Param("information") String information);
	
	@Query("SELECT new edu.udec.dto.ExamReportDto(ce.exam, ce.information) FROM ConsultExam ce WHERE ce.consult.id = :idConsult")
	public List<ExamReportDto> getId(@Param("idConsult") Integer idConsult);
	
}
