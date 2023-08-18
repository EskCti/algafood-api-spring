package com.eskcti.algafoodapi.infrastruct.services.email;

import com.eskcti.algafoodapi.core.email.EmailProperties;
import com.eskcti.algafoodapi.domain.services.SendEmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SmtpSendEmailService implements SendEmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailProperties emailProperties;

    @Value("${sendgrid.api.key}")
    private String apiKey;
    @Override
    public void send(Message message) {
        try {
            Email from = new Email(emailProperties.getSender());
            String subject = message.getSubject();
            Email to = new Email(message.getAddressee());
            Content content = new Content("text/html", message.getBody());

            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(apiKey);
            System.out.println("'SENDGRID_API_KEY' = " + apiKey);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() == 401) {
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
                throw new EmailException("Unable to send email");
            }
        } catch (Exception e) {
            throw new EmailException("Unable to send email", e);
        }
    }
}
