package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class UserModel extends RepresentationModel<UserModel> {
    private Long id;
    private String name;
    private String email;
}
