package com.eskcti.algafoodapi.infrastruct.services.email;

import com.eskcti.algafoodapi.domain.services.SendEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Slf4j
public class FakeSendEmailService extends SmtpSendEmailService {
    @Override
    public void send(Message message) {
        String corpo = processTemplate(message);

        log.info("[FAKE E-MAIL] Para: {}\n{}", message.getRecipients(), corpo);
    }
}
