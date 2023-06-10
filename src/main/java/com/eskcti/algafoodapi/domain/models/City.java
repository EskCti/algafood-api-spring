package com.eskcti.algafoodapi.domain.models;

import com.eskcti.algafoodapi.Groups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tab_cities")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @Valid
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    @NotNull
    @ConvertGroup(from = Default.class, to = Groups.StateId.class)
    private State state;
}
