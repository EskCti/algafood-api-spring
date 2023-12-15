package com.eskcti.algafoodapi.core.security;

import com.eskcti.algafoodapi.domain.repositories.OrderRepository;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AlgaSecurity {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("user_id");
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public boolean hasWrittenScope() {
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean hasReadingScope() {
        return hasAuthority("SCOPE_READ");
    }

    public boolean canConsultRestaurants() {
        return hasReadingScope() && isAuthenticated();
    }

    public boolean canManagerRegisterRestaurants() {
        return hasWrittenScope() && hasAuthority("EDIT_RESTAURANTS");
    }

    public boolean canManageRestaurantOperations(Long restaurantId) {
        return hasWrittenScope() && (hasAuthority("EDIT_RESTAURANTS") ||
                managerRestaurant(restaurantId));
    }

    public boolean canConsultUsersGroupsPermissions() {
        return hasReadingScope() && hasAuthority("CONSULT_USERS_GROUPS_PERMISSIONS");
    }

    public boolean canEditUsersGroupsPermissions() {
        return hasWrittenScope() && hasAuthority("EDIT_USERS_GROUPS_PERMISSIONS");
    }

    public boolean canFindOrders(Long customerId, Long restaurantId) {
        return hasReadingScope() && (
                hasAuthority("CONSULT_ORDERS") ||
                        userAuthenticatedEqual(customerId) ||
                        managerRestaurant(restaurantId));

    }

    public boolean canFindOrders() {
        return isAuthenticated() && hasReadingScope();
    }

    public boolean canConsultPaymentsType() {
        return isAuthenticated() && hasReadingScope();
    }

    public boolean canConsultCities() {
        return isAuthenticated() && hasReadingScope();
    }

    public boolean canConsultStates() {
        return isAuthenticated() && hasReadingScope();
    }

    public boolean canConsultKitchens() {
        return isAuthenticated() && hasReadingScope();
    }

    public boolean canConsultStatistics() {
        return hasReadingScope() && hasAuthority("GENERATE_REPORTS");
    }

    public boolean managerRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            return false;
        }
        var existsResponsible = restaurantRepository.existsResponsible(restaurantId, getUserId());
        System.out.println("*************" + existsResponsible + "******************");
        return existsResponsible;
    }

    public boolean managerOrder(String orderCode) {
        if (orderCode == null) {
            return false;
        }
        var orderManagerBy = orderRepository.isManagerBy(orderCode, getUserId());
        System.out.println("*************" + orderManagerBy + "******************");
        return orderManagerBy;
    }

    public boolean userAuthenticatedEqual(Long userId) {
        return getUserId() != null && userId != null
                && getUserId().equals(userId);
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }
    public boolean canManagerOrders(String orderCode) {
        return hasAuthority("SCOPE_WRITE") &&
                (hasAuthority("MANAGER_ORDERS") ||
                        managerOrder(orderCode));
    }
}
