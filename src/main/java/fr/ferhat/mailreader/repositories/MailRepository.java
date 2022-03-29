package fr.ferhat.mailreader.repositories;

import fr.ferhat.mailreader.models.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MailRepository extends MongoRepository<Mail, UUID> {

}
