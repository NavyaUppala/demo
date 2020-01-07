package com.capitalone.dept.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.dept.demo.model.Customer;

@RestController
@RequestMapping("/kafka")
public class KafkaControllers {

	@Value("${kafka.topic.avro}")
	private String avroTopic;

	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;

	@PostMapping("/sendCustomerToKafka")
	public ListenableFuture<SendResult<String, Customer>> sendCustomerToKafka(@RequestBody Customer customer) {
		System.out.println(customer.getFname());
		return kafkaTemplate.send(avroTopic, customer);
	}

}
