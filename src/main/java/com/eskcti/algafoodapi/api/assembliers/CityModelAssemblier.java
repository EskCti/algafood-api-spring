package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.CityModel;
import com.eskcti.algafoodapi.api.model.RestaurantModel;
import com.eskcti.algafoodapi.api.resources.CityController;
import com.eskcti.algafoodapi.api.resources.StateController;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityModelAssemblier extends RepresentationModelAssemblerSupport<City, CityModel> {

    @Autowired
    private ModelMapper modelMapper;

    public CityModelAssemblier() {
        super(CityController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        cityModel.add(linkTo(methodOn(CityController.class).list())
                .withRel("cities"));

        cityModel.getState().add(linkTo(methodOn(StateController.class)
                .find(cityModel.getState().getId())).withSelfRel());
        cityModel.getState().add(linkTo(methodOn(StateController.class).list())
                .withRel("states"));

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
