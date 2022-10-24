package com.br.challenge.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(length = 8, nullable = false)
	private String zipCode;

	@Column(length = 6, nullable = false)
	private int houseNumber;

	@JsonIgnore
	@ManyToMany(mappedBy = "addresses")
	private List<Customer> customers = new ArrayList<>();

	public List<Customer> getCustomers() {
		return customers;
	}
	
	public String getZipCode() {
		String zipCodeformatted = this.zipCode.substring(0, 5) + "-" + this.zipCode.substring(5);
		return zipCodeformatted;
	}

}
