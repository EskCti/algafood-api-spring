package com.eskcti.algafoodapi.domain.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;

public interface SendEmailService {

    void send(Message message);

    @Getter
    @Builder
    class Message {
        private String addressee;
        private String subject;
        private String body;

        @Singular
        private Map<String, Object> variables;
    }
}
