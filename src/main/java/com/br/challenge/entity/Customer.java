package com.br.challenge.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 11, nullable = false)
	private String documentId;

	@Column(length = 90, nullable = false)
	private String name;

	@Column(length = 3, nullable = false)
	private int age;

	@Column(nullable = false)
	private Date registrationDate;

	private Date lastUpdateInfo;

	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "customer_address",
            joinColumns = 
            	@JoinColumn(name = "customer_id"),
            inverseJoinColumns = 
            	@JoinColumn(name = "address_id"))
    private List<Address> addresses = new ArrayList<>();
	
    public List<Address> getAddresses() {
        return addresses;
    }    
}
