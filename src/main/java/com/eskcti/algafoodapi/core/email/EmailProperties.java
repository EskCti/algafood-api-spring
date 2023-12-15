package com.eskcti.algafoodapi.core.email;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotNull
    private String sender;

    @NotNull
    private Implement impl = Implement.FAKE;

    private String recipient;

    private Sandbox sandbox = new Sandbox();

    public enum Implement {
        SMTP, FAKE, SANDBOX
    }
    @Getter
    @Setter
    public class Sandbox {

        private String sender;

    }
}
