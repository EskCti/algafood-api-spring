package com.eskcti.algafoodapi;

import com.eskcti.algafoodapi.di.models.Customer;
import com.eskcti.algafoodapi.di.service.ActivationCustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFirstController {

    private ActivationCustomerService activationCustomerService;

    public MyFirstController(ActivationCustomerService activationCustomerService) {
        this.activationCustomerService = activationCustomerService;

        System.out.println("MyFirstController: "+ activationCustomerService);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Customer joao = new Customer("João", "joao@gmail.com", "123456789");
        activationCustomerService.activate(joao);
        return "Funciona remote mesmo blz blz blz!";
    }
}
