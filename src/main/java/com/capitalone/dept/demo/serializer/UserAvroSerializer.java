package com.capitalone.dept.demo.serializer;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.capitalone.dept.demo.model.User;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;

public class UserAvroSerializer implements Serializer<User> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAvroSerializer.class);

	@Override
	public byte[] serialize(String topic, User data) {
		try {
			byte[] result = null;

			if (data != null) {
				LOGGER.info("data='{}'", data);

				Schema schema = new Schema.Parser()
						.parse(new ClassPathResource("avro/user-v1.avsc").getInputStream());

			//	Schema raw = new Schema.Parser().setValidate(true).parse(schema.toString());
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