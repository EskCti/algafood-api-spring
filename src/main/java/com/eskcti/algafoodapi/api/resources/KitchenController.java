package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/kitchens", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class KitchenController {
    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> find(@PathVariable Long id) {
        Kitchen kitchen = kitchenRepository.find(id);
        if (kitchen != null) return ResponseEntity.status(HttpStatus.OK).body(kitchen);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        Kitchen kitchenUpdate = kitchenRepository.find(id);
        if (kitchenUpdate == null) return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(kitchen, kitchenUpdate, "id");

        kitchenRepository.save(kitchenUpdate);

        return ResponseEntity.ok(kitchenUpdate);
    }
}
