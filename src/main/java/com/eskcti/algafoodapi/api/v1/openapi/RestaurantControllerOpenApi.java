package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.RestaurantModel;
import com.eskcti.algafoodapi.api.v1.model.input.RestaurantInput;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Lista restaurantes", parameters = {
        @Parameter(name = "project",
            description = "Resumo da projeção",
            example = "summary",
            in = ParameterIn.QUERY,
            required = false
        )
    })
    CollectionModel<RestaurantModel> listSummary();
    MappingJacksonValue listMapping();

    @Operation(summary = "Busca um restaurante por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    RestaurantModel find(
            @Parameter(description = "ID de um restaurante", example = "1", required = true)
            @PathVariable Long id);

    @Operation(summary = "Cadastra um restaurante", responses = {
            @ApiResponse(responseCode = "201", description = "Restaurante cadastrado"),
    })
    RestaurantModel insert(@RequestBody @Valid RestaurantInput restaurantInput);

    @Operation(summary = "Atualiza um restaurante por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    RestaurantModel update(@PathVariable Long id, @RequestBody @Valid RestaurantInput restaurantInput);

    @Operation(summary = "Excluir um restaurante por ID")
    void remove(@PathVariable Long id);
    RestaurantModel updatePartial(
            @PathVariable Long restaurantId,
            @RequestBody Map<String, Object> fields,
            HttpServletRequest request
    );

    @Operation(summary = "Ativa um restaurante por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ResponseEntity<Void> activate (@PathVariable Long restaurantId);

    @Operation(summary = "Desativa um restaurante por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ResponseEntity<Void> deactivate (@PathVariable Long restaurantId);

    @Operation(summary = "Ativa múltiplos restaurantes", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso"),
    })
    void activateMultiples(@RequestBody List<Long> restaurantsIds);

    @Operation(summary = "Inativa múltiplos restaurantes", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso"),
    })
    void deactivateMultiples(@RequestBody List<Long> restaurantsIds);

    @Operation(summary = "Abre um restaurante por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ResponseEntity<Void> opening (@PathVariable Long restaurantId);

    @Operation(summary = "Fecha um restaurante por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema")) }),
    })
    ResponseEntity<Void> closing (@PathVariable Long restaurantId);
    void validate(Restaurant restaurant, String objectName);
    void merge(Map<String, Object> fieldsSource, Restaurant restaurantTarget, HttpServletRequest request);
}
