package org.ctb.restaurantservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ctb.common.Address;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRestaurantRequest {

    private String name;
    private Address address;
    private RestaurantMenu menu;
}
