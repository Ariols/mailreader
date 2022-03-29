package fr.ferhat.mailreader.controllers;

import fr.ferhat.mailreader.models.Mail;
import fr.ferhat.mailreader.repositories.MailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MailController {

    private final MailRepository mailRepository;

    public MailController(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @GetMapping("/api/mails")
    public ResponseEntity<List<Mail>> getAllMails() {
        return ResponseEntity.ok(mailRepository.findAll());
    }
}
