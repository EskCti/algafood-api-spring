package com.eskcti.algafoodapi.core.email;

import com.eskcti.algafoodapi.domain.services.SendEmailService;
import com.eskcti.algafoodapi.infrastruct.services.email.FakeSendEmailService;
import com.eskcti.algafoodapi.infrastruct.services.email.SandboxSendEmailService;
import com.eskcti.algafoodapi.infrastruct.services.email.SmtpSendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public SendEmailService sendEmailService() {
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeSendEmailService();
            case SMTP:
                return new SmtpSendEmailService();
            case SANDBOX:
                return new SandboxSendEmailService();
            default:
                return null;
        }
    }
}
