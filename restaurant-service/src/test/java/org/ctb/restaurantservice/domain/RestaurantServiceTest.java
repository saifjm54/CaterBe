package org.ctb.restaurantservice.domain;

import org.ctb.restaurantservice.RestaurantDetailsMother;
import org.ctb.restaurantservice.events.RestaurantCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
    private RestaurantDomainEventPublisher domainEventPublisher;

    public static RestaurantMenu menu;


    @BeforeEach
    public void setup() {
        restaurantRepository = mock(RestaurantRepository.class);
        domainEventPublisher = mock(RestaurantDomainEventPublisher.class);
        restaurantService = new RestaurantService(restaurantRepository,domainEventPublisher);
        menu = new RestaurantMenu();
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(RestaurantDetailsMother.CHICKEN_VINDALOO_MENU_ITEM);
        menu.setMenuItems(menuItemList);
    }

    @Test
    public void shouldCreateOrder(){
        when(restaurantRepository.save(any(Restaurant.class))).then(invocation -> {
            Restaurant restaurant = (Restaurant) invocation.getArgument(0);
            restaurant.setId(RestaurantDetailsMother.AJANTA_ID);
            return  restaurant;
        });
        CreateRestaurantRequest request = new CreateRestaurantRequest(RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,RestaurantDetailsMother.RESTAURANT_ADDRESS,menu);

        Restaurant restaurant = restaurantService.create(request);

        verify(restaurantRepository).save(same(restaurant));
        verify(domainEventPublisher).publish(restaurant, Collections.singletonList(new RestaurantCreated(RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,RestaurantDetailsMother.RESTAURANT_ADDRESS,menu)));
    }

    @Test
    public void shouldReturnRestaurantWithAnExistingId(){

        when(restaurantRepository.findById(RestaurantDetailsMother.AJANTA_ID)).thenReturn(
                Optional.of(new Restaurant(RestaurantDetailsMother.AJANTA_ID,RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,menu))
        );

        Optional<Restaurant> restaurant = restaurantService.findById(RestaurantDetailsMother.AJANTA_ID);

        assertEquals(RestaurantDetailsMother.AJANTA_ID, restaurant.get().getId());
        assertEquals(RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,restaurant.get().getName());
        assertEquals(menu,restaurant.get().getMenu());


    }


}
