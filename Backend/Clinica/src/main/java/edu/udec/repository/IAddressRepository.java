package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer> {

}
