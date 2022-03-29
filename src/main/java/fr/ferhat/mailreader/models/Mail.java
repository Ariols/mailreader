package fr.ferhat.mailreader.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("mails")
public class Mail {

    @Id
    private UUID id;
    private String dateReception;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDateReception() {
        return dateReception;
    }

    public void setDateReception(String dateReception) {
        this.dateReception = dateReception;
    }
}
