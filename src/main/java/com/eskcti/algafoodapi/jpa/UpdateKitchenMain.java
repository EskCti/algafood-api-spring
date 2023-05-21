package com.eskcti.algafoodapi.jpa;

import com.eskcti.algafoodapi.AlgafoodApiApplication;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class UpdateKitchenMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen1 = kitchenRepository.findById(1L).get();
        kitchen1.setName("Italiano");
        kitchen1 = kitchenRepository.save(kitchen1);

        Kitchen kitchen2 = kitchenRepository.findById(2L).get();
        kitchen2.setName("Havaiano");
        kitchen2 = kitchenRepository.save(kitchen2);

        System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
        System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
    }
}
