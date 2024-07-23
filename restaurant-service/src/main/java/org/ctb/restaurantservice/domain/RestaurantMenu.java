package org.ctb.restaurantservice.domain;


import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.List;

@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class RestaurantMenu {

    @ElementCollection
    private List<MenuItem> menuItems;
}
