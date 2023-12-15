package com.eskcti.algafoodapi.jpa;

import com.eskcti.algafoodapi.AlgafoodApiApplication;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class UpdateRestaurantMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant1 = restaurantRepository.findById(1L).get();
        restaurant1.setName("Restaurant 1 update");
        restaurant1 = restaurantRepository.save(restaurant1);

        Restaurant restaurant2 = restaurantRepository.findById(2L).get();
        restaurant2.setName("Restaurant 2 update");
        restaurant2 = restaurantRepository.save(restaurant2);

        System.out.printf("%d - %s\n", restaurant1.getId(), restaurant1.getName());
        System.out.printf("%d - %s\n", restaurant2.getId(), restaurant2.getName());
    }
}
