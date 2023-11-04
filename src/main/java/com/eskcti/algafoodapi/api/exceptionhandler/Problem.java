package com.eskcti.algafoodapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;


@JsonInclude(Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problem")
public class Problem {
    @Schema(example = "400")
    private Integer status;
    @Schema(example = "http://algafood.com.br/dados-invalidos")
    private String type;
    @Schema(example = "Dados inválidos")
    private String title;
    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String detail;

    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String userMessage;
    @Schema(example = "2023-10-23T06:41:00.000")
    private OffsetDateTime dateTime;
    @Schema(example = "Lista de objetos ou campos que geraram o erro")
    private List<Object> objects;

    @Getter
    @Builder
    @Schema(name = "ObjectProblem")
    public static class Object {
        @Schema(example = "preço")
        private String name;
        @Schema(example = "O preço é inválido")
        private String userMessage;
    }
}
