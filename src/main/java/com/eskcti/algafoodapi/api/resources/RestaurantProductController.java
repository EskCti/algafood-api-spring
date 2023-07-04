package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.ProductModelAssemblier;
import com.eskcti.algafoodapi.api.model.ProductModel;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/products", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class RestaurantProductController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductModelAssemblier modelAssemblier;

    @GetMapping
    public List<ProductModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        return modelAssemblier.toCollectionModel(restaurant.getProducts());
    }
}
