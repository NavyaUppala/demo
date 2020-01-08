package com.capitalone.dept.demo.serializer;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.capitalone.dept.demo.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;

public class CustomerAvroSerializer implements Serializer<Customer> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAvroSerializer.class);

	@Override
	public byte[] serialize(String topic, Customer data) {
		try {
			byte[] result = null;

			if (data != null) {
				LOGGER.info("data='{}'", new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(data));

				Schema schema = new Schema.Parser()
						.parse(new ClassPathResource("avro/customer-v1.avsc").getInputStream());
				AvroSchema avroSchema = new AvroSchema(schema);
				AvroMapper avroMapper = new AvroMapper();
				result =	avroMapper.writer(avroSchema).writeValueAsBytes(data);
			}
			return result;
		} catch (IOException ex) {
			throw new SerializationException("Can't serialize data='" + data + "' for topic='" + topic + "'", ex);
		}
	}
}