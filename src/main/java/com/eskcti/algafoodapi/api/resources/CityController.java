package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.StateNotFoundException;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public City find(@PathVariable Long id) {
        return cityService.find(id);
    }

    @PostMapping
    public City insert(@RequestBody @Valid City city) {
        try {
            return cityService.save(city);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public City update(@PathVariable Long id, @RequestBody @Valid City city) {
        try {
            City cityUpdate = cityService.find(id);
            BeanUtils.copyProperties(city, cityUpdate, "id");
            cityUpdate = cityService.save(cityUpdate);
            return cityUpdate;
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.remove(id);
    }
}
