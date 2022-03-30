package fr.ferhat.mailreader.services;

import com.azure.core.http.rest.RequestOptions;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.UserCollectionPage;
import fr.ferhat.mailreader.models.User;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GraphService {
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
        UserCollectionPage userCollectionPage = this.graphClient.users().buildRequest().get();
        if (userCollectionPage == null) {
           return Collections.emptyList();
        }
        return userCollectionPage.getCurrentPage().stream().map(User::fromGraphUser).collect(Collectors.toList());
    }
}
