package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.assembliers.ProductInputDisassembler;
import com.eskcti.algafoodapi.api.v1.assembliers.ProductModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.ProductModel;
import com.eskcti.algafoodapi.api.v1.model.input.ProductInput;
import com.eskcti.algafoodapi.api.v1.openapi.RestaurantProductControllerOpenApi;
import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.ProductService;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/v1/restaurants/{restaurantId}/products", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class RestaurantProductController implements RestaurantProductControllerOpenApi {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductModelAssemblier modelAssemblier;

    @Autowired
    private ProductInputDisassembler inputDisassembler;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<ProductModel> list(
            @PathVariable Long restaurantId,
            @RequestParam(required = false) Boolean includeInactive
    ) {
        log.log(Level.forName("CUSTOM", 350), "Restaurant id " + restaurantId);
        Restaurant restaurant = restaurantService.find(restaurantId);
        List<Product> products;

        if (includeInactive == null) {
            includeInactive = false;
        }

        if (includeInactive) {
            products = productService.findByRestaurant(restaurant);
        } else {
             products = productService.findActivesByRestaurant(restaurant);
        }

        return modelAssemblier.toCollectionModel(products)
                .add(algaLinks.linkToProductsByRestaurant(restaurantId, "products"));
    }

    @GetMapping("/{productId}")
    public ProductModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productService.findByRestaurantIdAndId(restaurantId, productId);

        return modelAssemblier.toModel(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId, @PathVariable Long productId) {
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
        Product productUpdate = productService.findByRestaurantIdAndId(restaurantId, productId);

        inputDisassembler.copyToDomainObject(productInput, productUpdate);
        return modelAssemblier.toModel(productService.save(productUpdate));
    }
}
