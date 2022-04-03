package fr.ferhat.mailreader.controllers;

import fr.ferhat.mailreader.models.User;
import fr.ferhat.mailreader.repositories.UserRepository;
import fr.ferhat.mailreader.services.SyncUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MailController {
    private static final Logger logger = LoggerFactory.getLogger(MailController.class);
    private final UserRepository userRepository;

    public MailController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
