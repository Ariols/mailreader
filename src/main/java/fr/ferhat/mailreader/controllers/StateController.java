package fr.ferhat.mailreader.controllers;

import fr.ferhat.mailreader.models.State;
import fr.ferhat.mailreader.models.User;
import fr.ferhat.mailreader.repositories.UserRepository;
import fr.ferhat.mailreader.services.StatesServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StateController {
    private static final Logger logger = LoggerFactory.getLogger(StateController.class);
    private final UserRepository userRepository;
    private final StatesServices statesServices;

    public StateController(UserRepository userRepository, StatesServices statesServices) {
        this.userRepository = userRepository;
        this.statesServices = statesServices;
    }

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(value = "/api/mail-importance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<State>> mailImportance() {
        return ResponseEntity.ok(statesServices.importanceCount());
    }

    @GetMapping(value = "/api/mail-count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<State>> mailCount() {
        return ResponseEntity.ok(statesServices.mailCount());
    }

    @GetMapping(value = "/api/mail-attachements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<State>> mailAttachement() {
        return ResponseEntity.ok(statesServices.attachementCount());
    }
}
