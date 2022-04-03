package fr.ferhat.mailreader.models;

import com.microsoft.graph.models.Importance;
import com.microsoft.graph.models.Message;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Document("user.mails")
public class Mail {

    @Id
    private String id;
    private OffsetDateTime recievedDateTime;
    private Boolean hasAttachements;
    private Importance importance;
    private String bodyPreview;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OffsetDateTime getRecievedDateTime() {
        return recievedDateTime;
    }

    public void setRecievedDateTime(OffsetDateTime recievedDateTime) {
        this.recievedDateTime = recievedDateTime;
    }

    public Boolean getHasAttachements() {
        return hasAttachements;
    }

    public void setHasAttachements(Boolean hasAttachements) {
        this.hasAttachements = hasAttachements;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public String getBodyPreview() {
        return bodyPreview;
    }

    public void setBodyPreview(String bodyPreview) {
        this.bodyPreview = bodyPreview;
    }

    public static Mail fromGraphMessage(Message message) {
        Mail mail = new Mail();
        mail.setId(message.id);
        mail.setRecievedDateTime(message.receivedDateTime);
        mail.setBodyPreview(message.bodyPreview);
        mail.setHasAttachements(message.hasAttachments);
        mail.setImportance(message.importance);
        return mail;
    }
}
