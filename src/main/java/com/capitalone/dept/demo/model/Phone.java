package com.capitalone.dept.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type;
	private String number;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_CUSTOMER_ID")
	private Customer customerPhone;

	public Phone() {
	}

	public Phone(String type, String number) {
		this.type = type;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Customer getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(Customer customerPhone) {
		this.customerPhone = customerPhone;
	}

}
