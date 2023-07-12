package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.OrderNotFoundException;
import com.eskcti.algafoodapi.domain.models.*;
import com.eskcti.algafoodapi.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssuanceOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentTypeService paymentTypeService;

    @Transactional
    public Order issuance(Order order) {
        validateOrder(order);
        validateItens(order);

        order.setShippingFee(order.getRestaurant().getShippingFee());
        order.calculateValueTotal();
        return orderRepository.save(order);
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    private void validateOrder(Order order) {
        City city = cityService.find(order.getAddressDelivery().getCity().getId());
        User customer = userService.find(order.getCustomer().getId());
        Restaurant restaurant = restaurantService.find(order.getRestaurant().getId());
        PaymentType paymentType = paymentTypeService.find(order.getPaymentType().getId());

        order.getAddressDelivery().setCity(city);
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setPaymentType(paymentType);

        if (restaurant.doesNotAcceptPaymentType(paymentType)) {
            throw new BusinessException(String.format("Payment method '%s' is not accepted by this restaurant.", paymentType.getDescription()));
        }
    }

    private void validateItens(Order order) {
        order.getItems().forEach(item -> {
            Product product =
                    productService.findByRestaurantIdAndId(
                            order.getRestaurant().getId(), item.getProduct().getId()
                    );
            item.setOrder(order);
            item.setProduct(product);
            item.setPriceUnit(product.getPrice());
        });
    }
}
