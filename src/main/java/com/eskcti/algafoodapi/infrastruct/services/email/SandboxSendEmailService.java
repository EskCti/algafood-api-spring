package com.eskcti.algafoodapi.infrastruct.services.email;

import com.eskcti.algafoodapi.core.email.EmailProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SandboxSendEmailService extends SmtpSendEmailService {

    @Autowired
    private EmailProperties emailProperties;
    @Override
    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        MimeMessage mimeMessage = super.createMimeMessage(message);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getSender());

        return mimeMessage;
    }
}
