package fr.ferhat.mailreader.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("user")
public class User {

    @Id
    private UUID id;
    private String nom;
    private String mail;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public static User fromGraphUser(com.microsoft.graph.models.User graphUser) {
        if(graphUser.id == null) {
            return null;
        }
        fr.ferhat.mailreader.models.User user = new User();
        user.setId(UUID.fromString(graphUser.id));
        user.setNom(graphUser.displayName);
        user.setMail(graphUser.mail);
        return user;
    }
}
