package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {
    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @GetMapping
    @Operation(hidden = true)
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        if (algaSecurity.canConsultKitchens()) {
            rootEntryPointModel.add(algaLinks.linkToKitchens());
        }

        if (algaSecurity.canFindOrders()) {
            rootEntryPointModel.add(algaLinks.linkToOrders());
        }

        if (algaSecurity.canConsultRestaurants()) {
            rootEntryPointModel.add(algaLinks.linkToRestaurants());
        }

        if (algaSecurity.canEditUsersGroupsPermissions()) {
            rootEntryPointModel.add(algaLinks.linkToGroups("groups"));
            rootEntryPointModel.add(algaLinks.linkToUsers());
        }

        if (algaSecurity.canConsultPaymentsType()) {
            rootEntryPointModel.add(algaLinks.linkToPaymentTypes());
        }

        if (algaSecurity.canConsultStates()) {
            rootEntryPointModel.add(algaLinks.linkToStates());
        }

        if (algaSecurity.canConsultCities()) {
            rootEntryPointModel.add(algaLinks.linkToCities());
        }

        if (algaSecurity.canConsultStatistics()) {
            rootEntryPointModel.add(algaLinks.linkToStatistics("statistics"));
        }

        return rootEntryPointModel;
    }
    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
