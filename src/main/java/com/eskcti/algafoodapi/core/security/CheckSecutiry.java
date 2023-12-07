package com.eskcti.algafoodapi.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecutiry {
    public @interface Kitchens {
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_KITCHENS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult {
        }
    }

    public @interface Restaurants {
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_RESTAURANTS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and " +
                "(hasAuthority('EDIT_RESTAURANTS') or " +
                "@algaSecurity.managerRestaurant(#restaurantId))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanManager {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult {
        }
    }
}
