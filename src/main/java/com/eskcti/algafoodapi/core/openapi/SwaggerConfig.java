package com.eskcti.algafoodapi.core.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(info()).externalDocs(
                        new ExternalDocumentation()
                                .description("Edson Shideki Kokado")
                                .url("http://www.seusite.com.br"));
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach((httpMethod, operation) -> {
                                ApiResponses responses = operation.getResponses();
                                switch (httpMethod) {
                                    case GET:
                                        responses.addApiResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), new ApiResponse()
                                                .description("Recurso Não encontrado"));
                                        responses.addApiResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()), new ApiResponse()
                                                    .description("Recurso não possui representação que poderia ser aceita pelo consumidor"));
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .description("Erro interno do servidor"));
                                        break;
                                    case POST:
                                        responses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), new ApiResponse()
                                                .description("Requisição inválida"));
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .description("Erro interno do servidor"));
                                        break;
                                    case PUT:
                                        responses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), new ApiResponse()
                                                .description("Requisição inválida"));
                                        responses.addApiResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), new ApiResponse()
                                                .description("Recurso Não encontrado"));
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .description("Erro interno do servidor"));
                                        break;
                                    case DELETE:
                                        responses.addApiResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), new ApiResponse()
                                                .description("Recurso Não encontrado"));
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .description("Erro interno do servidor"));
                                        break;
                                    default:
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .description("Erro interno do servidor"));
                                        break;
                                }
                            }));
        };
    }

//    @Bean
    public OperationCustomizer customOperationCustomizer() {
        return (operation, handlerMethod) -> {
            addGlobalResponses(operation, handlerMethod);
            return operation;
        };
    }

    private void addGlobalResponses(Operation operation, HandlerMethod handlerMethod) {
        Map<String, MediaType> content = new HashMap<>();
        content.put("application/json", new MediaType().schema(new Schema()));

        operation.getResponses()
                .addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                        .description("Erro interno do servidor"))
                .addApiResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()), new ApiResponse()
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor"))
                .addApiResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), new ApiResponse()
                        .description("Recurso Não encontrado"));
    }
    private Info info() {
        return new Info()
                .title("AlgaFood API")
                .description("API aberta para clientes e restaurantes")
                .version("1.0")
                .contact( new Contact())
                .contact(contact())
                .termsOfService("Termo de uso: Open Source")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.seusite.com.br"));
    }

    private Contact contact() {
        Contact contact = new Contact();
        contact.setName("Algaworks");
        contact.url("https://www.algaworks.com");
        contact.setEmail("contato@algaworks.com");
        return contact;
    }
}
