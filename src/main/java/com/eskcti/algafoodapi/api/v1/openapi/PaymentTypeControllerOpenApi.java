package com.eskcti.algafoodapi.api.v1.openapi;

import com.eskcti.algafoodapi.api.v1.model.PaymentTypeModel;
import com.eskcti.algafoodapi.api.v1.model.input.PaymentTypeInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.ServletWebRequest;

@Tag(name = "Forma de pagamentos", description = "Gerencia as formas de pagamentos")
public interface PaymentTypeControllerOpenApi {

    @Operation(summary = "Listar as formas de pagamentos")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de formas de pagamentos")
    )
    ResponseEntity<CollectionModel<PaymentTypeModel>> list(ServletWebRequest request);

    @Operation(summary = "Buscar uma cidade por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna a cidade"),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID da cidade inválida",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    ResponseEntity<PaymentTypeModel> find(
            ServletWebRequest request,
            @Parameter(description = "ID da forma de pagamento a ser pesquisada", example = "1", required = true)
            @PathVariable Long id);

    @Operation(summary = "Adicionar nova forma de pagamento", description = "Cadastro de uma forma de pagamento")
    PaymentTypeModel create(@RequestBody @Valid PaymentTypeInput paymentTypeInput);

    @Operation(summary = "Atualizar a forma de pagamento por ID")
    PaymentTypeModel update(
            @Parameter(description = "ID da forma de pagamento a ser alterada", example = "1", required = true)
            @PathVariable Long id,
            @RequestBody PaymentTypeInput paymentTypeInput);

    @Operation(summary = "Excluir a forma de pagamento por ID")
    void delete(
            @Parameter(description = "ID da forma de pagamento a ser excluida", example = "1", required = true)
            Long id
    );
}
