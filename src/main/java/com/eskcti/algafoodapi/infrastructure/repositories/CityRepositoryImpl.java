package com.eskcti.algafoodapi.infrastructure.repositories;

import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.repositories.CityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list() {
        return manager.createQuery("from City", City.class)
                .getResultList();
    }

    @Override
    public City find(Long id) {
        return manager.find(City.class, id);
    }

    @Override
    @Transactional
    public City save(City city) {
        return manager.merge(city);
    }

    @Override
    @Transactional
    public void remove(City city) {
        city = find(city.getId());
        manager.remove(city);
    }
}
