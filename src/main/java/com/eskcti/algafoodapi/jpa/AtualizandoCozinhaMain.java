package com.eskcti.algafoodapi.jpa;

import com.eskcti.algafoodapi.AlgafoodApiApplication;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AtualizandoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        Kitchen kitchen1 = cadastroCozinha.find(1L);
        kitchen1.setName("Italiano");
        kitchen1 = cadastroCozinha.save(kitchen1);

        Kitchen kitchen2 = cadastroCozinha.find(2L);
        kitchen2.setName("Havaiano");
        kitchen2 = cadastroCozinha.save(kitchen2);

        System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
        System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
    }
}
