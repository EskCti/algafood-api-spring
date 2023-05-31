package com.eskcti.algafoodapi.jpa;

import com.eskcti.algafoodapi.AlgafoodApiApplication;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.repositories.PaymentTypeRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ListPaymentsTypeMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentTypeRepository paymentTypeRepository = applicationContext.getBean(PaymentTypeRepository.class);

        List<PaymentType> paymentTypes = paymentTypeRepository.findAll();

        for (PaymentType paymentType : paymentTypes) {
            System.out.println(paymentType.getDescription());
        }
    }
}
