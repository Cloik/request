package com.br.challenge.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.challenge.entity.Customer;
import com.br.challenge.repository.CustomerRepository;
import com.br.challenge.service.CustomerResgistrationService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerResgistrationService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping
	public List<Customer> listAllCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("/find_customer_id/{customerId}")
	public Customer getCustomersById(@PathVariable Long customerId) {
		return customerService.foundOrError(customerId);
	}

	@GetMapping("/find_customer_name/{name}")
	public List<Customer> getCustomerByName(@PathVariable String name) {
		return customerRepository.findByName(name);
	}

	@PutMapping("/update_customer/{customerId}")
	public void updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
		Customer currentCustomer = customerService.foundOrError(customerId);

		BeanUtils.copyProperties(customer, currentCustomer, "id", "addresses");

		customerService.saveCustomer(currentCustomer);
	}

	@PostMapping("/add_customer")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
	}

	@DeleteMapping("delete_customer/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
	}

	@GetMapping("/find_customer_zipCode/{zipCode}")
	public List<Customer> getCustomerByZipCode(@PathVariable String zipCode) {
		return customerRepository.findCustomerByZipCode(zipCode);
	}

}
