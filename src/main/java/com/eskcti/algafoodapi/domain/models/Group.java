package com.eskcti.algafoodapi.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tab_groups")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToMany()
    @JoinTable(name = "tab_groups_permissions",
        joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

    public Boolean associatePermission(Permission permission) {
        return this.getPermissions().add(permission);
    }

    public Boolean disassociatePermission(Permission permission) {
        return this.getPermissions().remove(permission);
    }
}
