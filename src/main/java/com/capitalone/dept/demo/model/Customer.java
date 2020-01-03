package com.capitalone.dept.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer customerId;

	private String fname;
	private String lname;
	private String age;
	private LocalDateTime accountOpenDate;
	private Double acctBalance;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id", unique = true)
	private Address address;

	@OneToMany(mappedBy = "customerPhone")
	private List<Phone> phones;

	public Customer() {
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public LocalDateTime getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(LocalDateTime accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	public Double getAcctBalance() {
		return acctBalance;
	}

	public void setAcctBalance(Double acctBalance) {
		this.acctBalance = acctBalance;
	}

}
