package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.KitchenNotFoundException;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KitchenService {
    public static final String KITCHEN_NOT_REMOVED_IN_USE = "Kitchen with id %d not removed in use ";
    @Autowired
    private KitchenRepository kitchenRepository;

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void remove(Long id) {
        try {
            this.find(id);
            kitchenRepository.deleteById(id);
            kitchenRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(KITCHEN_NOT_REMOVED_IN_USE, id));
        }
    }

    public Kitchen find(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));
    }

    public Page<Kitchen> list(Pageable pageable) {
        return kitchenRepository.findAll(pageable);
    }
}
