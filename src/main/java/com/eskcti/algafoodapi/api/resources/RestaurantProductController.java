package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.ProductInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.ProductModelAssemblier;
import com.eskcti.algafoodapi.api.model.ProductModel;
import com.eskcti.algafoodapi.api.model.input.ProductInput;
import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.ProductService;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/products", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class RestaurantProductController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductModelAssemblier modelAssemblier;

    @Autowired
    private ProductInputDisassembler inputDisassembler;

    @GetMapping
    public List<ProductModel> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        return modelAssemblier.toCollectionModel(restaurant.getProducts());
    }

    @GetMapping("/{productId}")
    public ProductModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.findByRestaurantIdAndId(restaurantId, productId);

        return modelAssemblier.toModel(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Restaurant restaurant = restaurantService.find(restaurantId);
        productService.findByRestaurantIdAndId(restaurantId, productId);

        productService.remove(productId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel insert(@PathVariable Long restaurantId, @RequestBody ProductInput productInput) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        Product product = inputDisassembler.toDomainObject(productInput);
        product.setRestaurant(restaurant);
        return modelAssemblier.toModel(productService.save(product));
    }

    @PutMapping("/{productId}")
    public ProductModel update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody ProductInput productInput) {
        Restaurant restaurant = restaurantService.find(restaurantId);
        Product productUpdate = productService.findByRestaurantIdAndId(restaurantId, productId);

        inputDisassembler.copyToDomainObject(productInput, productUpdate);
        return modelAssemblier.toModel(productService.save(productUpdate));
    }
}
