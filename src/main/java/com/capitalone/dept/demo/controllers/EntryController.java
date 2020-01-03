package com.capitalone.dept.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.dept.demo.model.Customer;

@RestController
public class EntryController {

	
	@PostMapping("/createUser")
	public Customer createUser(@RequestBody Customer user) {
		return user;
	}
}
