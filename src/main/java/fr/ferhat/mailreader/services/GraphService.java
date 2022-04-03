package fr.ferhat.mailreader.services;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.http.GraphServiceException;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.MessageCollectionPage;
import com.microsoft.graph.requests.UserCollectionPage;
import fr.ferhat.mailreader.models.Mail;
import fr.ferhat.mailreader.models.User;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GraphService {
    private static final String RESOURCE_NOT_FOUND_ERROR = "ResourceNotFound";
    private static final String RESOURCE_NOT_AVAILABLE = "MailboxNotEnabledForRESTAPI";

    private static final Logger logger = LoggerFactory.getLogger(GraphService.class);
    private final GraphServiceClient<Request> graphClient;

    public GraphService(
            @Value("${app.graph.tenant-id}") String tenantId,
            @Value("${app.graph.client-id}") String clientId,
            @Value("${app.graph.client-secret}") String clientSecret) {
        this.graphClient = GraphServiceClient
                .builder()
                .authenticationProvider(
                        new TokenCredentialAuthProvider(
                                new ClientSecretCredentialBuilder()
                                        .clientId(clientId)
                                        .clientSecret(clientSecret)
                                        .tenantId(tenantId)
                                        .build()))
                .buildClient();
    }

    public List<User> fetchUsers() {
        // Paginate content
        UserCollectionPage userCollectionPage = this.graphClient.users().buildRequest().get();
        if (userCollectionPage == null) {
            return Collections.emptyList();
        }
        return userCollectionPage.getCurrentPage().stream().map(User::fromGraphUser).collect(Collectors.toList());
    }

    public List<Mail> fetchMails(User user) {
        try {
            MessageCollectionPage messageCollectionPage = this.graphClient.users(user.getId().toString()).messages().buildRequest().get();
            if (messageCollectionPage == null) {
                return Collections.emptyList();
            }
            return messageCollectionPage.getCurrentPage().stream().map(Mail::fromGraphMessage).toList();
        } catch (GraphServiceException exception) {
            if (exception.getError() != null
                    && exception.getError().error != null
                    && (Objects.equals(exception.getError().error.code, RESOURCE_NOT_FOUND_ERROR)
                        || Objects.equals(exception.getError().error.code, RESOURCE_NOT_AVAILABLE))) {
                return Collections.emptyList();
            }
            logger.error("Erreur lors de la récupération des mails de l'utilisateur {} - {}", user.getNom(), user.getMailAdresse(), exception);
            throw exception;
        }
    }
}
