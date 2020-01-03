package com.capitalone.dept.demo.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.dept.demo.model.Customer;
import com.capitalone.dept.demo.repo.CustomerRepo;

@RestController
@RequestMapping("/entry")
public class EntryController {

	@Autowired
	private CustomerRepo customerRepo;

	@PostMapping("/createUser")
	public Customer createUser(@RequestBody Customer customer) {
		return customerRepo.save(customer);
	}

	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer() {
		return customerRepo.findAll();
	}
	@GetMapping("/getCustomer")
	public Customer getCustomer() {
		Customer customer = new Customer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String formatDateTime = now.format(formatter);
		customer.setAccountOpenDate(now);
		return customer;
	}
}
