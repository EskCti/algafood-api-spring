package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping("/kitchens")
    public List<Kitchen> listByName(@RequestParam String name) {
        return kitchenRepository.findByName(name);
    }
}
