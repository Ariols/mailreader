package fr.ferhat.mailreader.repositories;

import fr.ferhat.mailreader.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
}
