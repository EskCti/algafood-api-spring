package com.eskcti.algafoodapi.core.security.authorizationserver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
public class JwtKeyStoreProperties {

    private String password;

    private String keypairAlias;

    private Resource jksLocation;
}
