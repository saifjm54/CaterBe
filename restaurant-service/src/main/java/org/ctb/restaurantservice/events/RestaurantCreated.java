package org.ctb.restaurantservice.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ctb.common.Address;
import org.ctb.restaurantservice.domain.RestaurantMenu;

@Data
@NoArgsConstructor
public class RestaurantCreated implements RestaurantDomainEvent{
    private String name;
    private Address address;
    private RestaurantMenu menu;

    public RestaurantCreated(String name, Address address, RestaurantMenu menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;

        if (menu == null)
            throw new NullPointerException("Null Menu");
        if (address == null)
            throw new NullPointerException("Null address");
    }
}
