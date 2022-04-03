package fr.ferhat.mailreader.models;

import com.microsoft.graph.requests.MessageCollectionPage;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document("user")
public class User {

    @Id
    private UUID id;
    private String nom;
    private String mailAdresse;
    // lazy fetching ?
    private List<Mail> mails;
    private Integer mailsCount = 0;

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

    public String getMailAdresse() {
        return mailAdresse;
    }

    public void setMailAdresse(String mailAdresse) {
        this.mailAdresse = mailAdresse;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }

    public Integer getMailsCount() {
        return mailsCount;
    }

    public void setMailsCount(Integer mailsCount) {
        this.mailsCount = mailsCount;
    }

    public static User fromGraphUser(com.microsoft.graph.models.User graphUser) {
        if(graphUser.id == null) {
            return null;
        }
        fr.ferhat.mailreader.models.User user = new User();
        user.setId(UUID.fromString(graphUser.id));
        user.setNom(graphUser.displayName);
        user.setMailAdresse(graphUser.mail);
        MessageCollectionPage messages = graphUser.messages;
        return user;
    }
}
