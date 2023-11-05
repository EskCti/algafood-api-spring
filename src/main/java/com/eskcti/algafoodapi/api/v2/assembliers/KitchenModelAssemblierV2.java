package com.eskcti.algafoodapi.api.v2.assembliers;

import com.eskcti.algafoodapi.api.v2.AlgaLinksV2;
import com.eskcti.algafoodapi.api.v2.model.KitchenModelV2;
import com.eskcti.algafoodapi.api.v2.resources.KitchenControllerV2;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class KitchenModelAssemblierV2 extends RepresentationModelAssemblerSupport<Kitchen, KitchenModelV2> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinksV2 algaLinks;

    public KitchenModelAssemblierV2() {
        super(KitchenControllerV2.class, KitchenModelV2.class);
    }
    public KitchenModelV2 toModel(Kitchen kitchen) {
        KitchenModelV2 kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        kitchenModel.add(algaLinks.linkToKitchens());

        return kitchenModel;
    }

    @Override
    public CollectionModel<KitchenModelV2> toCollectionModel(Iterable<? extends Kitchen> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(KitchenControllerV2.class).withSelfRel());
    }

//    public List<KitchenModel> toCollectionModel(List<Kitchen> kitchens) {
//        return kitchens.stream()
//                .map(kitchen -> toModel(kitchen))
//                .collect(Collectors.toList());
//    }
}
