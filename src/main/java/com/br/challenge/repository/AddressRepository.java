package com.br.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.challenge.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>  {

}
