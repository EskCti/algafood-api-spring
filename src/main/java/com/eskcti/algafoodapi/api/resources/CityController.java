package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.exceptionhandler.Problem;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.exceptions.StateNotFoundException;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.services.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public City insert(@RequestBody City city) {
        try {
            return cityService.save(city);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public City update(@PathVariable Long id, @RequestBody City city) {
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        Problem problem = Problem.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        Problem problem = Problem.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }
}
