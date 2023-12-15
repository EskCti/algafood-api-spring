package com.eskcti.algafoodapi.api.v1.openapi;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Tag(name = "Links")
public class LinksModelOpenApi {
    private LinkModel rel;

    @Getter
    @Setter
    @Tag(name = "Link")
    private class LinkModel {
        private String href;
        private boolean templated;
    }
}
