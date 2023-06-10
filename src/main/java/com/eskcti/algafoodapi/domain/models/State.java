package com.eskcti.algafoodapi.domain.models;

import com.eskcti.algafoodapi.core.validation.Groups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tab_states")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class State {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Groups.StateId.class)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 60)
    private String name;
}
