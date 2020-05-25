package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {
	
	Users findOneByNick(String nick);

}
