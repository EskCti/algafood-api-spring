package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.KitchenModel;
import com.eskcti.algafoodapi.api.v1.resources.KitchenController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class KitchenModelAssemblier extends RepresentationModelAssemblerSupport<Kitchen, KitchenModel> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public KitchenModelAssemblier() {
        super(KitchenController.class, KitchenModel.class);
    }
    public KitchenModel toModel(Kitchen kitchen) {
        KitchenModel kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        if (algaSecurity.canConsultKitchens()) {
            kitchenModel.add(algaLinks.linkToKitchens());
        }

        return kitchenModel;
    }

    @Override
    public CollectionModel<KitchenModel> toCollectionModel(Iterable<? extends Kitchen> entities) {
        CollectionModel<KitchenModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultKitchens()) {
            collectionModel
                    .add(linkTo(KitchenController.class).withSelfRel());
        }
        return collectionModel;
    }

//    public List<KitchenModel> toCollectionModel(List<Kitchen> kitchens) {
//        return kitchens.stream()
//                .map(kitchen -> toModel(kitchen))
//                .collect(Collectors.toList());
//    }
}
