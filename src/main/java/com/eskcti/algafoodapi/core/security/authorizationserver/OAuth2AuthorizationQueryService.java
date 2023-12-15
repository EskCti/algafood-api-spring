package com.eskcti.algafoodapi.core.security.authorizationserver;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.List;

public interface OAuth2AuthorizationQueryService {
    List<RegisteredClient> clientListWithConsent(String principalName);
}
