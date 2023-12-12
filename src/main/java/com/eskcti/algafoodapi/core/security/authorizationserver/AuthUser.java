package com.eskcti.algafoodapi.core.security.authorizationserver;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String fullName;
    public AuthUser(
            com.eskcti.algafoodapi.domain.models.User user,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.userId = user.getId();
        this.fullName = user.getName();
    }
}
