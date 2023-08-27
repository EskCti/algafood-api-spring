package com.eskcti.algafoodapi.core.openapi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info()).externalDocs(
                        new ExternalDocumentation()
                                .description("Edson Shideki Kokado")
                                .url("http://www.seusite.com.br"));
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
