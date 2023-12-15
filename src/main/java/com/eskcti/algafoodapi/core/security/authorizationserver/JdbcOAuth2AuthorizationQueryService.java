package com.eskcti.algafoodapi.core.security.authorizationserver;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.List;


public class JdbcOAuth2AuthorizationQueryService implements OAuth2AuthorizationQueryService{

    private final JdbcOperations jdbcOperations;
    private final RowMapper<RegisteredClient> registeredClientRowMapper;
    private final RowMapper<OAuth2Authorization> authorizationRowMapper;

    private final String LIST_AUTHORIZED_CLIENTS = "select rc.* from oauth2_authorization_consent c " +
            "inner join oauth2_registered_client rc on rc.id = c.registered_client_id " +
            "where c.principal_name = ? ";

    private final String LIST_AUTHORIZATIONS = "select oa.* from oauth2_authorization oa " +
            "inner join oauth2_registered_client orc on oa.registered_client_id = orc.id " +
            "where oa.principal_name = ? " +
            "and orc.client_id = ? ";

    public JdbcOAuth2AuthorizationQueryService(JdbcOperations jdbcOperations, RegisteredClientRepository repository) {
        this.jdbcOperations = jdbcOperations;
        this.registeredClientRowMapper = new JdbcRegisteredClientRepository.RegisteredClientRowMapper();
        this.authorizationRowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(repository);
    }

    @Override
    public List<RegisteredClient> clientListWithConsent(String principalName) {
        return this.jdbcOperations.query(LIST_AUTHORIZED_CLIENTS, registeredClientRowMapper, principalName);
    }

    @Override
    public List<OAuth2Authorization> listAuthorizations(String principalName, String clientId) {
        return this.jdbcOperations.query(LIST_AUTHORIZATIONS, authorizationRowMapper, principalName, clientId);
    }
}
