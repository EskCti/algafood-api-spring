package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.KitchenModel;
import com.eskcti.algafoodapi.api.resources.KitchenController;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class KitchenModelAssemblier extends RepresentationModelAssemblerSupport<Kitchen, KitchenModel> {
    @Autowired
    private ModelMapper modelMapper;
    public KitchenModelAssemblier() {
        super(KitchenController.class, KitchenModel.class);
    }
    public KitchenModel toModel(Kitchen kitchen) {
        KitchenModel kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        kitchenModel.add(linkTo(KitchenController.class)
                .withRel("kitchens"));

        return kitchenModel;
    }

    @Override
    public CollectionModel<KitchenModel> toCollectionModel(Iterable<? extends Kitchen> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(KitchenController.class).withSelfRel());
    }

//    public List<KitchenModel> toCollectionModel(List<Kitchen> kitchens) {
//        return kitchens.stream()
//                .map(kitchen -> toModel(kitchen))
//                .collect(Collectors.toList());
//    }
}
