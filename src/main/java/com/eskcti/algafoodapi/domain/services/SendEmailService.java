package com.eskcti.algafoodapi.domain.services;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

public interface SendEmailService {

    void send(Message message);

    @Getter
    @Builder
    class Message {
        private Set<String> addressee;
        private String subject;
        private String body;
    }
}
