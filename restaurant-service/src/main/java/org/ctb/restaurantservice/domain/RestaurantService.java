package org.ctb.restaurantservice.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ctb.restaurantservice.events.RestaurantCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final  RestaurantRepository restaurantRepository;
    private final  RestaurantDomainEventPublisher restaurantDomainEventPublisher;

    public Restaurant create(CreateRestaurantRequest request) {

        Restaurant restaurant = new Restaurant(request.getName(),request.getMenu());

        restaurantRepository.save(restaurant);
        restaurantDomainEventPublisher.publish(restaurant, Collections.singletonList(new RestaurantCreated(request.getName(),request.getAddress(),request.getMenu())));
        return restaurant;
    }



}
