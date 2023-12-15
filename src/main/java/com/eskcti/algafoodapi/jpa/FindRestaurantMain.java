package com.eskcti.algafoodapi.jpa;

import com.eskcti.algafoodapi.AlgafoodApiApplication;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class FindRestaurantMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant1 = restaurantRepository.findById(1L).get();

        Restaurant restaurant2 = restaurantRepository.findById(2L).get();

        System.out.printf("%d - %s - %.2f - %s\n",
                restaurant1.getId(),
                restaurant1.getName(),
                restaurant1.getShippingFee(),
                restaurant1.getKitchen().getName()
        );
        System.out.printf("%d - %s - %.2f - %s\n",
                restaurant2.getId(),
                restaurant2.getName(),
                restaurant2.getShippingFee(),
                restaurant2.getKitchen().getName()
        );
    }
}
