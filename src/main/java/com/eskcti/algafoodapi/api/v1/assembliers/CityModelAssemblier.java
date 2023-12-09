package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.AlgaLinks;
import com.eskcti.algafoodapi.api.v1.model.CityModel;
import com.eskcti.algafoodapi.api.v1.resources.CityController;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public CityModelAssemblier() {
        super(CityController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        if (algaSecurity.canConsultCities()) {
            cityModel.add(algaLinks.linkToCities());
        }

        if (algaSecurity.canConsultStates()) {
            cityModel.getState().add(algaLinks.linkToState(cityModel.getState().getId()));
            cityModel.getState().add(algaLinks.linkToStates());
        }

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        CollectionModel<CityModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultCities()) {
            collectionModel
                    .add(linkTo(CityController.class).withSelfRel());
        }
        return collectionModel;
    }

//    public List<CityModel> toCollectionModel(List<City> cities) {
//        return cities.stream()
//                .map(city -> toModel(city))
//                .collect(Collectors.toList());
//    }
}
