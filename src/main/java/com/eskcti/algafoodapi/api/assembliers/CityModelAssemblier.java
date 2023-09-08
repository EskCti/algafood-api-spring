package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.AlgaLinks;
import com.eskcti.algafoodapi.api.model.CityModel;
import com.eskcti.algafoodapi.api.resources.CityController;
import com.eskcti.algafoodapi.domain.models.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CityModelAssemblier extends RepresentationModelAssemblerSupport<City, CityModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public CityModelAssemblier() {
        super(CityController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        cityModel.add(algaLinks.linkToCities());

        cityModel.getState().add(algaLinks.linkToState(cityModel.getState().getId()));
        cityModel.getState().add(algaLinks.linkToStates());

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CityController.class).withSelfRel());
    }

//    public List<CityModel> toCollectionModel(List<City> cities) {
//        return cities.stream()
//                .map(city -> toModel(city))
//                .collect(Collectors.toList());
//    }
}
