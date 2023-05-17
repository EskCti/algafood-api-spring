package com.eskcti.algafoodapi.jpa;

import com.eskcti.algafoodapi.AlgafoodApiApplication;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.repositories.CityRepository;
import com.eskcti.algafoodapi.domain.repositories.StateRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ListStatesMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        StateRepository stateRepository = applicationContext.getBean(StateRepository.class);

        List<State> states = stateRepository.list();

        for (State state : states) {
            System.out.println(state.getName());
        }
    }
}
