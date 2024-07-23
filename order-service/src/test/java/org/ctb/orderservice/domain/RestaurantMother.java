package org.ctb.orderservice.domain;

import org.ctb.common.Address;
import org.ctb.common.Money;

import java.util.Collections;
import java.util.List;

public class RestaurantMother {
    public static final String AJANTA_RESTAURANT_NAME = "Ajanta";
    public static final long AJANTA_ID = 1L;

    public static final String CHICKEN_VINDALOO = "Chicken Vindaloo";
    public static final String CHICKEN_VINDALOO_MENU_ITEM_ID = "1";
    public static final Money CHICKEN_VINDALOO_PRICE = new Money("12.34");
    public static final Address RESTAURANT_ADDRESS = new Address("1 Main Street", "Unit 99", "Oakland", "CA", "94611");

    public static MenuItem CHICKEN_VINDALOO_MENU_ITEM = new MenuItem(CHICKEN_VINDALOO_MENU_ITEM_ID, CHICKEN_VINDALOO, CHICKEN_VINDALOO_PRICE);

    public static final List<MenuItem> AJANTA_RESTAURANT_MENU_ITEMS = Collections.singletonList(CHICKEN_VINDALOO_MENU_ITEM);

    public static final Restaurant AJANTA_RESTAURANT =
            new Restaurant(AJANTA_ID, AJANTA_RESTAURANT_MENU_ITEMS, AJANTA_RESTAURANT_NAME);

    public static RestaurantCreated makeAjantaRestaurantCreatedEvent() {
        return new RestaurantCreated().withName(AJANTA_RESTAURANT_NAME)
                .withAddress(RestaurantEventMapper.fromAddress(RESTAURANT_ADDRESS))
                .withMenu(new Menu().withMenuItems(RestaurantEventMapper.fromMenuItems(AJANTA_RESTAURANT_MENU_ITEMS)));
    }
}
