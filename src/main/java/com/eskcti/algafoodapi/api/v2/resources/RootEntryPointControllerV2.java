package com.eskcti.algafoodapi.api.v2.resources;

import com.eskcti.algafoodapi.api.v2.AlgaLinksV2;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v2", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointControllerV2 {
    @Autowired
    private AlgaLinksV2 algaLinks;

    @GetMapping
    @Operation(hidden = true)
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algaLinks.linkToKitchens());
        rootEntryPointModel.add(algaLinks.linkToCities());

        return rootEntryPointModel;
    }
    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
