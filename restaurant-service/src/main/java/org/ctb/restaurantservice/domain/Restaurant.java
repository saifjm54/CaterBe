package org.ctb.restaurantservice.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurants")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private RestaurantMenu menu;

    public Restaurant(String name, RestaurantMenu menu) {
        this.name = name;
        this.menu = menu;
    }
}
