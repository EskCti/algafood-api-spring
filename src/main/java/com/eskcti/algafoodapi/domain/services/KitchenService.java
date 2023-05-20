package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService {
    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void remove(Long id) {
        try {
            kitchenRepository.remove(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Kitchen with id %d not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Kitchen with id %d not removed in use ", id));
        }
    }

    public Kitchen find(Long id) {
        try {
            return kitchenRepository.find(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Kitchen with id %d not found", id));
        }
    }

    public List<Kitchen> list() {
        return kitchenRepository.list();
    }
}
