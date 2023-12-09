package com.eskcti.algafoodapi.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecutiry {
    public @interface Kitchens {
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_KITCHENS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanConsult {
        }
    }

    public @interface PaymentsType {
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_PAYMENTS_TYPE')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanConsult {
        }
    }


    public @interface Cities {
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_CITIES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanConsult {
        }
    }

    public @interface States {
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_STATES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanConsult {
        }
    }

    public @interface Restaurants {
        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_RESTAURANTS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and " +
                "(hasAuthority('EDIT_RESTAURANTS') or " +
                "@algaSecurity.managerRestaurant(#restaurantId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanManager {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanConsult {
        }
    }

    public @interface Orders {
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULT_ORDERS') or " +
                "@algaSecurity.userAuthenticatedEqual(returnObject.customer.id) or " +
                "@algaSecurity.managerRestaurant(returnObject.restaurant.id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanFind {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and " +
                "(hasAuthority('CONSULT_ORDERS') or " +
                "@algaSecurity.userAuthenticatedEqual(#orderFilter.customerId) or " +
                "@algaSecurity.managerRestaurant(#orderFilter.restaurantId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanList {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanCreate { }

        @PreAuthorize("@algaSecurity.canManagerOrders(#orderCode)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanManager {
        }
    }

    public @interface UsersGroupsPermissions {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
                + "@algaSecurity.userAuthenticatedEqual(#id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanUpdateOwnPassword { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_USERS_GROUPS_PERMISSIONS') or "
                + "@algaSecurity.userAuthenticatedEqual(#id))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanUpdateUser { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_USERS_GROUPS_PERMISSIONS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanEdit { }


        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULT_USERS_GROUPS_PERMISSIONS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanConsult { }
    }

    public @interface Statistics {
        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GENERATE_REPORTS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface CanConsult { }
    }
}
