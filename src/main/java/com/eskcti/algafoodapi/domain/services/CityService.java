package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.repositories.CityRepository;
import com.eskcti.algafoodapi.domain.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    public static final String NOT_FOUND_STATE_INTO_CITY_REQUEST = "Not found state into city request";
    public static final String NOT_FOUND_STATE_WITHOUT_ID = "Not found state without id";
    public static final String NOT_FOUND_STATE_WITH_ID = "Not found State with id %d";
    public static final String NOT_REMOVED_IN_USE = "City with id %d not removed in use";
    public static final String NOT_FOUND_CITY_WITH_ID = "Not found city with id %d";
    @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;

    public List<City> list() {
        return cityRepository.findAll();
    }

    public City save(City city) {
        if (city.getState() == null) {
            throw new EntityNotFoundException(NOT_FOUND_STATE_INTO_CITY_REQUEST);
        }
        Long stateId = city.getState().getId();
        if (stateId == null) {
            throw new EntityNotFoundException(NOT_FOUND_STATE_WITHOUT_ID);
        }
        try {
            State state = stateRepository.findById(stateId)
                    .orElseThrow(() ->
                            new EntityNotFoundException(String.format(NOT_FOUND_STATE_WITH_ID, stateId)
                            )
                    );
            city.setState(state);
            return cityRepository.save(city);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(NOT_FOUND_STATE_WITH_ID, stateId));
        }
    }

    public void remove(Long id) {
        try {
            find(id);
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(NOT_REMOVED_IN_USE, id));
        }
    }

    public City find(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_CITY_WITH_ID, id)));
        return city;
    }
}
