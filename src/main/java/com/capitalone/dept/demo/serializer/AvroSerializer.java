package com.capitalone.dept.demo.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AvroSerializer.class);

  @Override
  public void close() {
    // No-op
  }

  @Override
  public void configure(Map<String, ?> arg0, boolean arg1) {
    // No-op
  }

  @Override
  public byte[] serialize(String topic, T data) {
    try {
      byte[] result = null;

      if (data != null) {
        LOGGER.debug("data='{}'", data);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder =
            EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
      
		Schema mainSchema = new Schema.Parser().parse(new ClassPathResource("avro/user-v1.avsc").getInputStream());
		GenericRecord avroMessage = new GenericData.Record(mainSchema);
		
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(mainSchema);
        datumWriter.write(data, binaryEncoder);

        binaryEncoder.flush();
        byteArrayOutputStream.close();

        result = byteArrayOutputStream.toByteArray();
        LOGGER.debug("serialized data='{}'", DatatypeConverter.printHexBinary(result));
      }
      return result;
    } catch (IOException  ex) {
      throw new SerializationException(
          "Can't serialize data='" + data + "' for topic='" + topic + "'", ex);
    }
  }
}