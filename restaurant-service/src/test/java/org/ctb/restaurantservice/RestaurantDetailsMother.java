package org.ctb.restaurantservice;

import org.ctb.common.Address;
import org.ctb.common.Money;
import org.ctb.restaurantservice.domain.MenuItem;
import org.ctb.restaurantservice.domain.RestaurantMenu;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailsMother {

    public static final String AJANTA_RESTAURANT_NAME = "Ajanta";
    public static final long AJANTA_ID = 1L;
    public static final String CHICKEN_VINDALOO = "Chicken Vindaloo";
    public static final String CHICKEN_VINDALOO_MENU_ITEM_ID = "1";
    public static final Money CHICKEN_VINDALOO_PRICE = new Money("12.34");
    public static final Address RESTAURANT_ADDRESS = new Address("1 Main Street", "Unit 99", "Oakland", "CA", "94611");

    public static MenuItem CHICKEN_VINDALOO_MENU_ITEM = new MenuItem(CHICKEN_VINDALOO_MENU_ITEM_ID, CHICKEN_VINDALOO, CHICKEN_VINDALOO_PRICE);

    public static RestaurantMenu getRestaurantMenu() {
        RestaurantMenu restaurantMenu = new RestaurantMenu();
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(RestaurantDetailsMother.CHICKEN_VINDALOO_MENU_ITEM);
        restaurantMenu.setMenuItems(menuItemList);
        return restaurantMenu;
    }

}
