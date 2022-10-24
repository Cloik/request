package com.br.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.challenge.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByName(String name);

	@Query("from Customer a join fetch a.addresses b where b.zipCode = :zipCode")
	List<Customer> findCustomerByZipCode(String zipCode);

}
