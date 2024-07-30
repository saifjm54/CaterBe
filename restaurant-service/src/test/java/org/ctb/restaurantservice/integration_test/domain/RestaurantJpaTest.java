package org.ctb.restaurantservice.integration_test.domain;

import org.ctb.restaurantservice.RestaurantDetailsMother;
import org.ctb.restaurantservice.domain.Restaurant;
import org.ctb.restaurantservice.domain.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = RestaurantJpaTestConfiguration.class)
public class RestaurantJpaTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void shouldSaveAndLoadRestaurant() {

        Long restaurantId = transactionTemplate.execute(
                (ts) -> {
                    Restaurant restaurant = new Restaurant(RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,RestaurantDetailsMother.getRestaurantMenu());
                    restaurantRepository.save(restaurant);
                    return restaurant.getId();
                }
        );

        transactionTemplate.execute(
                (ts) -> {
                    Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

                    assertNotNull(restaurant);
                    assertEquals(RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,restaurant.getName());
                    assertEquals(RestaurantDetailsMother.getRestaurantMenu(),restaurant.getMenu());
                    return null;
                }
        );
    }
}
