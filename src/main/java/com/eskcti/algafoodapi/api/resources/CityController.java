package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.services.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cities", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> list() {
        return cityService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        try {
            City city = cityService.find(id);
            return ResponseEntity.ok(city);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody City city) {
        try {
            city = cityService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).body(city);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody City city) {
        try {
            City cityUpdate = cityService.find(id);
            BeanUtils.copyProperties(city, cityUpdate, "id");
            cityUpdate = cityService.save(cityUpdate);
            return ResponseEntity.ok(cityUpdate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            cityService.remove(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityInUseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
