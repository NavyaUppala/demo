package com.capitalone.dept.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String stree1;
	private String Street2;
	private String apt;
	private String city;
	private String state;
	private String Country;
	private String zipCode;

	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Customer customer;

	public Address() {
	}

	public Address(String stree1, String street2, String apt, String city, String state, String country,
			String zipCode) {
		this.stree1 = stree1;
		Street2 = street2;
		this.apt = apt;
		this.city = city;
		this.state = state;
		Country = country;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStree1() {
		return stree1;
	}

	public void setStree1(String stree1) {
		this.stree1 = stree1;
	}

	public String getStreet2() {
		return Street2;
	}

	public void setStreet2(String street2) {
		Street2 = street2;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
