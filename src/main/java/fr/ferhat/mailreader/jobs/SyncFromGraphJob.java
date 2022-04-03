package fr.ferhat.mailreader.jobs;


import fr.ferhat.mailreader.services.SyncUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SyncFromGraphJob {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SyncUsers syncUsers;

    public SyncFromGraphJob(SyncUsers syncUsers) {
        this.syncUsers = syncUsers;
    }

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.MINUTES)
    private void execute() {
        try {
            syncUsers.syncUsersWithMails();
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de synchronisation des mails utilisateurs", e);
        }
    }
}
