package com.capitalone.dept.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.capitalone.dept.demo.model.Customer;
import com.capitalone.dept.demo.model.User;
import com.capitalone.dept.demo.serializer.CustomerAvroSerializer;
import com.capitalone.dept.demo.serializer.UserAvroSerializer;

@Configuration
public class KafkaSenderConfig {

	  @Value("${kafka.bootstrap-servers}")
	  private String bootstrapServers;
	  
	  public Map<String, Object> customerProducerConfigs() {
	    Map<String, Object> props = new HashMap<>();

	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomerAvroSerializer.class);
		props.put("auto-offset-reset: earliest", "earliest");
		props.put("acks", "1");
		props.put("retries", "10");
	    return props;
	  }
	  
	  public ProducerFactory<String, Customer> customerProducerFactory() {
	    return new DefaultKafkaProducerFactory<>(customerProducerConfigs());
	  }

	  @Bean
	  @Primary
	  public KafkaTemplate<String, Customer> customerkafkaTemplate() {
	    return new KafkaTemplate<>(customerProducerFactory());
	  }

	  public Map<String, Object> userProducerConfigs() {
	    Map<String, Object> props = new HashMap<>();

	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserAvroSerializer.class);
		props.put("auto-offset-reset: earliest", "earliest");
		props.put("acks", "1");
		props.put("retries", "10");
	    return props;
	  }
	  

	  public ProducerFactory<String, User> userProducerFactory() {
	    return new DefaultKafkaProducerFactory<>(userProducerConfigs());
	  }

	  @Bean
	  public KafkaTemplate<String, User> userKafkaTemplate() {
	    return new KafkaTemplate<>(userProducerFactory());
	  }
	
}
