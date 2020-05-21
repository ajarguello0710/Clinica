package edu.udec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.udec.dto.ConsultListDto;
import edu.udec.entity.Consult;

@Repository
public interface IConsultRepository extends JpaRepository<Consult, Integer> {
	
//	@Query("SELECT c.id, c.name, c.date FROM Consult c")
//	public List<Consult> obtnerConsultas();
	
	Page<Consult> findByName(String name, Pageable pageable);
	
	public Page<Consult> findByDoctor_NameIgnoreCaseContaining(String name, Pageable pageable); 
	
//	@Query(value = "SELECT consult "
//			+ "FROM consult JOIN doctor ON consult.fk_id_doctor = doctor.id"
//			+ "WHERE doctor.name LIKE :nameDoctor", nativeQuery = true)
//	public Page<Consult> listarPorNombreDoctor(@Param("nameDoctor") String nameDoctor, Pageable pageable);
	
//	@Query("SELECT c FROM Consult JOIN c.doctor d ON c.doctor.id = d.id WHERE c.doctor.nombre LIKE :name"+"%")
//	public Page<Consult> findNameDoctor(@Param("name") String name, Pageable pageable);
	
	
//	Page<Consult> findByNameLike(String name, Pageable pageable);
//	@Query(value = "SELECT consult FROM consult JOIN doctor WHERE ON consult.doctor = doctor.id ON  doctor.name LIKE :name%", nativeQuery = true)
	
//	@Query(value = "SELECT consult.id, consult.date, consult.name "
//			+ "FROM consult "
//			+ " WHERE consult.name LIKE ?1", nativeQuery = true)
//	public Page<Consult> findByNameLike(@Param("name") String name, Pageable pageable);
}
