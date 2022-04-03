package fr.ferhat.mailreader.services;

import fr.ferhat.mailreader.models.Mail;
import fr.ferhat.mailreader.models.State;
import fr.ferhat.mailreader.models.User;
import fr.ferhat.mailreader.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StatesServices {

    private final UserRepository userRepository;

    public StatesServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<State> mailCount() {
        return this.userRepository.findAll()
                .stream().collect(Collectors.groupingBy(
                        User::getMailsCount, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new State(entry.getKey() == 0 ? "Aucun" : "Avec", entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<State> attachementCount() {
        return this.userRepository.findAll()
                .stream()
                .flatMap(user -> Objects.requireNonNullElse(user.getMails(), new ArrayList<Mail>()).stream())
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Mail::getHasAttachements, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new State(entry.getKey() ? "Avec piece jointe" : "Sans piece", entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<State> importanceCount() {
        // Faire une requête mongo
        return this.userRepository.findAll()
                .stream()
                .flatMap(user -> Objects.requireNonNullElse(user.getMails(), new ArrayList<Mail>()).stream())
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Mail::getImportance, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new State(entry.getKey().toString(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
