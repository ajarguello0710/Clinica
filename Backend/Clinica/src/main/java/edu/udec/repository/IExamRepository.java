package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Exam;

@Repository
public interface IExamRepository extends JpaRepository<Exam, Integer> {

}
