package com.appdirect.kafkapocmaven.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class SerializationService {
//    public byte[] serialize(final MicrosoftTenantEvent payload) {
//        final DatumWriter<AvroHttpRequest> writer = new SpecificDatumWriter<>(MicrosoftTenantEvent.class);
//        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        try {
//            final Encoder encoder = EncoderFactory.get().jsonEncoder(MicrosoftTenantEvent.getClassSchema(), stream);
//            writer.write(payload, encoder);
//            encoder.flush();
//            return stream.toByteArray();
//        } catch (final IOException e) {
//            throw new AvroRuntimeException("Avro message could not be serialized", e);
//        }
//    }
//
//    public MicrosoftTenantEvent deserialize(final byte[] data) {
//        final DatumReader<MicrosoftTenantEvent> reader = new SpecificDatumReader<>(MicrosoftTenantEvent.class);
//        try {
//            final Decoder decoder = DecoderFactory.get().binaryDecoder(MicrosoftTenantEvent.getClassSchema(), null);
//            return reader.read(null, decoder);
//        } catch (IOException e) {
//            throw new AvroRuntimeException("Avro message could not be deserialized", e);
//        }
//    }
}
