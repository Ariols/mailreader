package fr.ferhat.mailreader.configuration;

import org.springframework.core.convert.converter.Converter;

import java.time.OffsetDateTime;

public class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {
    @Override
    public OffsetDateTime convert(String source) {
        try {
            return OffsetDateTime.parse(source);
        } catch (Exception e) {
            return null;
        }
    }
}
