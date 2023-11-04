package com.eskcti.algafoodapi.core.openapi;

import com.eskcti.algafoodapi.api.exceptionhandler.Problem;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SwaggerConfig {

    private static final String badRequestResponse = "BadRequestResponse";
    private static final String notFoundResponse = "NotFoundResponse";
    private static final String notAcceptableResponse = "NotAcceptableResponse";
    private static final String internalServerErrorResponse = "InternalServerErrorResponse";
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(info())
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Edson Shideki Kokado")
                                .url("http://www.seusite.com.br"))
                .components(new Components()
                        .schemas(
                            generateSchemas()
                        )
                        .responses(generateResponses())
                );
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
                                                .$ref(notFoundResponse));
                                        responses.addApiResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()), new ApiResponse()
                                                .$ref(notAcceptableResponse));
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .$ref(internalServerErrorResponse));
                                        break;
                                    case POST:
                                        responses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                                new ApiResponse().$ref(badRequestResponse));
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .$ref(internalServerErrorResponse));
                                        break;
                                    case PUT:
                                        responses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                                new ApiResponse().$ref(badRequestResponse));
                                        responses.addApiResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), new ApiResponse()
                                                .$ref(notFoundResponse));
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .$ref(internalServerErrorResponse));
                                        break;
                                    case DELETE:
                                        responses.addApiResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), new ApiResponse()
                                                .$ref(notFoundResponse));
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .$ref(internalServerErrorResponse));
                                        break;
                                    default:
                                        responses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), new ApiResponse()
                                                .$ref(internalServerErrorResponse));
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
//        content.put("application/json", new MediaType().schema(new Schema()));

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

    private Map<String, Schema> generateSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();
        Map<String, Schema> problemSchema =
                ModelConverters.getInstance().read(Problem.class);
        Map<String, Schema> problemObjectSchema =
                ModelConverters.getInstance().read(Problem.Object.class);

        schemaMap.putAll(problemSchema);
        schemaMap.putAll(problemObjectSchema);
        return schemaMap;
    }

    private Map<String, ApiResponse> generateResponses() {
        final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(
                        MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType()
                                .schema(new Schema<Problem>().$ref("Problem"))
                );

        apiResponseMap.put(badRequestResponse, new ApiResponse()
                .description("Requisição inválida")
                .content(content));

        apiResponseMap.put(notFoundResponse, new ApiResponse()
                .description("Recurso Não encontrado")
                .content(content));

        apiResponseMap.put(notAcceptableResponse, new ApiResponse()
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .content(content));

        apiResponseMap.put(notAcceptableResponse, new ApiResponse()
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .content(content));

        apiResponseMap.put(internalServerErrorResponse, new ApiResponse()
                .description("Erro interno do servidor")
                .content(content));

        return apiResponseMap;
    }

}
