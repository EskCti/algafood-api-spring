package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.City;

import java.util.List;

public interface CityRepository {
    List<City> list();
    City find(Long id);
    City save(City city);
    void remove(City city);
}
