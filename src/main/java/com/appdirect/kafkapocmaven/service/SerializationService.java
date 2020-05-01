package com.appdirect.kafkapocmaven.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Service;

import com.appdirect.kafkapocmaven.model.CspTokenEvent;
import com.appdirect.kafkapocmaven.model.MicrosoftTenantEvent;

@Service
public class SerializationService {
    public byte[] serialize(final MicrosoftTenantEvent payload) {
        final DatumWriter<MicrosoftTenantEvent> writer = new SpecificDatumWriter<>(MicrosoftTenantEvent.class);
        return serialize(payload, writer);
    }

    public byte[] serialize(final CspTokenEvent payload) {
        final DatumWriter<CspTokenEvent> writer = new SpecificDatumWriter<>(CspTokenEvent.class);
        return serialize(payload, writer);
    }

    private <T extends SpecificRecordBase> byte[] serialize(final T payload, final DatumWriter<T> writer) {
        byte[] byteArr;

        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            final BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(out, null);
            writer.write(payload, encoder);
            encoder.flush();
            byteArr = out.toByteArray();

        } catch (IOException e) {
            throw new AvroRuntimeException("Avro message could not be serialized", e);
        }
        return byteArr;
    }

    public MicrosoftTenantEvent deserialize1(final byte[] payload) {
        final DatumReader<MicrosoftTenantEvent> reader = new SpecificDatumReader<>(MicrosoftTenantEvent.class);
        return deserialize(payload, reader);
    }

    public CspTokenEvent deserialize2(final byte[] payload) {
        final DatumReader<CspTokenEvent> reader = new SpecificDatumReader<>(CspTokenEvent.class);
        return deserialize(payload, reader);
    }

    private <T> T deserialize(final byte[] payload, final DatumReader<T> reader) {
        try {
            final Decoder decoder = DecoderFactory.get().binaryDecoder(payload, null);
            return reader.read(null, decoder);
        } catch (final IOException e) {
            throw new AvroRuntimeException("Avro message could not be deserialized", e);
        }
    }
}
