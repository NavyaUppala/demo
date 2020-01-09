package com.capitalone.dept.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.dept.demo.model.Customer;
import com.capitalone.dept.demo.model.User;
import com.capitalone.dept.demo.repo.CustomerRepo;

@RestController
@RequestMapping("/kafka")
public class KafkaControllers {

	@Value("${kafka.topic.avro.customer}")
	private String customerTopic;
	
	@Value("${kafka.topic.avro.user}")
	private String userTopic;

	@Autowired
	@Qualifier(value = "customerkafkaTemplate")
	private KafkaTemplate<String, Customer> kafkaTemplate;
	
	@Autowired
	@Qualifier(value = "userKafkaTemplate")
	private KafkaTemplate<String, User> userKafkaTemplate;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@PostMapping("/sendCustomerToKafka/{id}")
	public ListenableFuture<SendResult<String, Customer>> sendCustomerToKafka(@PathVariable("id") int id) {
		Optional<Customer> customer = customerRepo.findById(id);
		System.out.println("Sending cutomer to topic");
		return customer.isPresent() ? kafkaTemplate.send(customerTopic, "customer" , customer.get()) : null;
	}
	
	@PostMapping("/sendUserToKafka")
	public ListenableFuture<SendResult<String, User>> sendUserToKafka(@RequestBody User user) {

		return userKafkaTemplate.send(userTopic, "user" , user);
	}

}
