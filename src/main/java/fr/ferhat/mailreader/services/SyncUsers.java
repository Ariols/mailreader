package fr.ferhat.mailreader.services;


import fr.ferhat.mailreader.models.Mail;
import fr.ferhat.mailreader.models.User;
import fr.ferhat.mailreader.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncUsers {
    private static final Logger logger = LoggerFactory.getLogger(SyncUsers.class);
    private final GraphService graphService;
    private final UserRepository userRepository;

    public SyncUsers(GraphService graphService, UserRepository userRepository) {
        this.graphService = graphService;
        this.userRepository = userRepository;
    }

    public void syncUsersWithMails() {
        List<User> users = this.graphService.fetchUsers();
        for (User user : users) {
            if(user.getMailAdresse() == null) {
                continue;
            }
            List<Mail> userMails = graphService.fetchMails(user);
            if(!userMails.isEmpty()) {
                user.setMails(userMails);
                user.setMailsCount(userMails.size());
            }
        }
        userRepository.saveAll(users);
    }
}
