package com.eskcti.algafoodapi.jpa;

import com.eskcti.algafoodapi.AlgafoodApiApplication;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class AddRestaurantMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Restaurant 3");
        restaurant1.setShippingFee(BigDecimal.valueOf(15));

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Restaurant 4");
        restaurant2.setShippingFee(BigDecimal.valueOf(20));

        restaurant1 = restaurantRepository.save(restaurant1);
        restaurant2 = restaurantRepository.save(restaurant2);

        System.out.printf("%d - %s - %.2f\n", restaurant1.getId(), restaurant1.getName(), restaurant1.getShippingFee());
        System.out.printf("%d - %s - %.2f\n", restaurant2.getId(), restaurant2.getName(), restaurant2.getShippingFee());
    }
}
