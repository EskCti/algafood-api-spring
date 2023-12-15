package com.eskcti.algafoodapi.api.v2.assembliers;

import com.eskcti.algafoodapi.api.v2.AlgaLinksV2;
import com.eskcti.algafoodapi.api.v2.model.CityModelV2;
import com.eskcti.algafoodapi.api.v2.resources.CityControllerV2;
import com.eskcti.algafoodapi.domain.models.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CityModelAssemblierV2 extends RepresentationModelAssemblerSupport<City, CityModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinksV2 algaLinks;

    public CityModelAssemblierV2() {
        super(CityControllerV2.class, CityModelV2.class);
    }

    @Override
    public CityModelV2 toModel(City city) {
        CityModelV2 cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        cityModel.add(algaLinks.linkToCities());

//        cityModel.getState().add(algaLinks.linkToState(cityModel.getState().getId()));
//        cityModel.getState().add(algaLinks.linkToStates());

        return cityModel;
    }

    @Override
    public CollectionModel<CityModelV2> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CityControllerV2.class).withSelfRel());
    }

//    public List<CityModel> toCollectionModel(List<City> cities) {
//        return cities.stream()
//                .map(city -> toModel(city))
//                .collect(Collectors.toList());
//    }
}
