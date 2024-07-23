package org.ctb.restaurantservice.domain;

import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.ctb.common.Address;
import org.ctb.restaurantservice.RestaurantDetailsMother;
import org.ctb.restaurantservice.events.RestaurantCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
    private RestaurantDomainEventPublisher domainEventPublisher;


    @BeforeEach
    public void setup() {
        restaurantRepository = mock(RestaurantRepository.class);
        domainEventPublisher = mock(RestaurantDomainEventPublisher.class);
        restaurantService = new RestaurantService(restaurantRepository,domainEventPublisher);
    }

    @Test
    public void shouldCreateOrder(){
        when(restaurantRepository.save(any(Restaurant.class))).then(invocation -> {
            Restaurant restaurant = (Restaurant) invocation.getArgument(0);
            restaurant.setId(RestaurantDetailsMother.AJANTA_ID);
            return  restaurant;
        });

        RestaurantMenu menu = new RestaurantMenu();
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(RestaurantDetailsMother.CHICKEN_VINDALOO_MENU_ITEM);
        menu.setMenuItems(menuItemList);
        CreateRestaurantRequest request = new CreateRestaurantRequest(RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,RestaurantDetailsMother.RESTAURANT_ADDRESS,menu);

        Restaurant restaurant = restaurantService.create(request);

        verify(restaurantRepository).save(same(restaurant));verify(domainEventPublisher).publish(restaurant, Collections.singletonList(new RestaurantCreated(RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,RestaurantDetailsMother.RESTAURANT_ADDRESS,menu)));
    }
}
