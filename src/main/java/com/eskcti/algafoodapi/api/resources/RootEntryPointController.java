package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.AlgaLinks;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    @Operation(hidden = true)
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algaLinks.linkToKitchens());
        rootEntryPointModel.add(algaLinks.linkToOrders());
        rootEntryPointModel.add(algaLinks.linkToRestaurants());
        rootEntryPointModel.add(algaLinks.linkToGroups("groups"));
        rootEntryPointModel.add(algaLinks.linkToUsers());
        rootEntryPointModel.add(algaLinks.linkToPaymentTypes());
        rootEntryPointModel.add(algaLinks.linkToStates());
        rootEntryPointModel.add(algaLinks.linkToStates());
        rootEntryPointModel.add(algaLinks.linkToCities());
        rootEntryPointModel.add(algaLinks.linkToStatistics("statistics"));

        return rootEntryPointModel;
    }
    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
