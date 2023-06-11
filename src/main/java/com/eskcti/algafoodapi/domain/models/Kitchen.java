package com.eskcti.algafoodapi.domain.models;

import com.eskcti.algafoodapi.core.validation.Groups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tab_kitchens")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Kitchen {
    @NotNull(groups = Groups.KitchenId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;
}
