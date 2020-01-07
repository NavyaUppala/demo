package com.capitalone.dept.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.capitalone.dept.demo.model.Customer;
import com.capitalone.dept.demo.serializer.AvroSerializer;

@Configuration
public class KafkaSenderConfig {

	  @Value("${kafka.bootstrap-servers}")
	  private String bootstrapServers;
	  
	  @Bean
	  public Map<String, Object> producerConfigs() {
	    Map<String, Object> props = new HashMap<>();

	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
		props.put("auto-offset-reset: earliest", "earliest");
		props.put("acks", "1");
		props.put("retries", "10");
	    return props;
	  }
	  
	  @Bean
	  public ProducerFactory<String, Customer> producerFactory() {
	    return new DefaultKafkaProducerFactory<>(producerConfigs());
	  }

	  @Bean
	  public KafkaTemplate<String, Customer> kafkaTemplate() {
	    return new KafkaTemplate<>(producerFactory());
	  }
	
}
