package com.br.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.challenge.entity.Customer;
import com.br.challenge.exception.EntityExistingException;
import com.br.challenge.exception.EntityInUseException;
import com.br.challenge.exception.EntityNotFoundException;
import com.br.challenge.repository.CustomerRepository;

@Service
public class CustomerResgistrationService {

	private static final String MSG_ENTITY_IN_USE = "EntityInUseException - Customer id %d can't be delete";

	private static final String MSG_ENTITY_NOT_FOUND = "EntityNotFoundException - Customer id %d doesn't found";	
	
	private static final String MSG_EXISTING_CUSTOMER = "EntityExistingException - Existing customer";

	@Autowired
	private CustomerRepository customerRepository;

	public void saveCustomer(Customer customer) {

		List<Customer> customers = customerRepository.findAll();

		customers.stream().filter(result -> result.getDocumentId().equals(customer.getDocumentId())).findAny()
				.ifPresentOrElse(result -> {
					throw new EntityExistingException(MSG_EXISTING_CUSTOMER);

				}, () -> {
					customerRepository.save(customer);
				});
	}

	public void deleteCustomer(Long customerId) {
		try {
			customerRepository.deleteById(customerId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(MSG_ENTITY_NOT_FOUND, customerId));

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_ENTITY_IN_USE, customerId));
		}
	}

	public Customer foundOrError(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_ENTITY_NOT_FOUND, customerId)));
	}
}
