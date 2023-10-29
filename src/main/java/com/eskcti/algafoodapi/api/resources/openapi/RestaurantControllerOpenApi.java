package com.eskcti.algafoodapi.api.resources.openapi;

import com.eskcti.algafoodapi.api.model.RestaurantModel;
import com.eskcti.algafoodapi.api.model.input.RestaurantInput;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Tag(name = "Restaurantes", description = "Gerencia os restaurantes")
public interface RestaurantControllerOpenApi {

    @Operation(parameters = {
        @Parameter(name = "project",
            description = "Resumo da projeção",
            example = "summary",
            in = ParameterIn.QUERY,
            required = false
        )
    })
    CollectionModel<RestaurantModel> listSummary();
    MappingJacksonValue listMapping();
    RestaurantModel find(@PathVariable Long id);
    RestaurantModel insert(@RequestBody @Valid RestaurantInput restaurantInput);
    RestaurantModel update(@PathVariable Long id, @RequestBody @Valid RestaurantInput restaurantInput);
    void remove(@PathVariable Long id);
    RestaurantModel updatePartial(
            @PathVariable Long restaurantId,
            @RequestBody Map<String, Object> fields,
            HttpServletRequest request
    );
    ResponseEntity<Void> activate (@PathVariable Long restaurantId);
    ResponseEntity<Void> deactivate (@PathVariable Long restaurantId);
    void activateMultiples(@RequestBody List<Long> restaurantsIds);
    void deactivateMultiples(@RequestBody List<Long> restaurantsIds);
    ResponseEntity<Void> opening (@PathVariable Long restaurantId);
    ResponseEntity<Void> closing (@PathVariable Long restaurantId);
    void validate(Restaurant restaurant, String objectName);
    void merge(Map<String, Object> fieldsSource, Restaurant restaurantTarget, HttpServletRequest request);
}
