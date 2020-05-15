package edu.udec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import edu.udec.entity.Consult;

@Repository
public interface IConsultRepository extends JpaRepository<Consult, Integer> {
	
	@Query("SELECT c FROM Consult c")
	public List<Consult> getConsults();
	
}
