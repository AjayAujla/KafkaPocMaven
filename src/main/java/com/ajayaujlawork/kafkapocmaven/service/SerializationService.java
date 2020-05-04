package com.ajayaujlawork.kafkapocmaven.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.springframework.stereotype.Service;

@Service
public class SerializationService {

    public <T> byte[] serialize(final T payload, final Class<T> clazz) {
        final DatumWriter<T> writer = new SpecificDatumWriter<>(clazz);
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            final BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(out, null);
            writer.write(payload, encoder);
            encoder.flush();
            return out.toByteArray();
        } catch (final IOException e) {
            throw new AvroRuntimeException("Avro message could not be serialized", e);
        }
    }

    public <T> T deserialize(final byte[] payload, final Class<T> clazz) {
        final DatumReader<T> reader = new SpecificDatumReader<>(clazz);
        try {
            final Decoder decoder = DecoderFactory.get().binaryDecoder(payload, null);
            return reader.read(null, decoder);
        } catch (final IOException e) {
            throw new AvroRuntimeException("Avro message could not be deserialized", e);
        }
    }
}
