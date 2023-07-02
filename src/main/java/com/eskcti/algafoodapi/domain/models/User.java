package com.eskcti.algafoodapi.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tab_users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 250)
    private String email;

    @Column(nullable = false, length = 40)
    private String password;

    @ManyToMany
    @JoinTable(name = "tab_users_groups",
        joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )

    private List<Group> groups;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updatedAt;

    public boolean passwordMatchWith(String password) {
        return getPassword().equals(password);
    }

    public boolean passwordNotMatchWith(String password) {
        return !passwordMatchWith(password);
    }
}
