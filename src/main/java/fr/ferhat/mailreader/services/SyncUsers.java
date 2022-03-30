package fr.ferhat.mailreader.services;


import fr.ferhat.mailreader.models.User;
import fr.ferhat.mailreader.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncUsers {

    private final GraphService graphService;
    private final UserRepository userRepository;

    public SyncUsers(GraphService graphService, UserRepository userRepository) {
        this.graphService = graphService;
        this.userRepository = userRepository;
    }

    public void syncUser() {
        List<User> users = this.graphService.fetchUsers();
        this.userRepository.saveAll(users);
    }
}
