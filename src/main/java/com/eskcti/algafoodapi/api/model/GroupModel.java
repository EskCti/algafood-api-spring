package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class GroupModel extends RepresentationModel<GroupModel> {
    private Long id;
    private String name;
}
