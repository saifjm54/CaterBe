package org.ctb.orderservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ctb.orderserviceapi.events.OrderDomainEvent;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "order_service_restaurants")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Restaurant {

    @Id
    private Long id;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "order_service_restaurant_menu_items")
    private List<MenuItem> menuItems;
    private String name;

    public List<OrderDomainEvent> reviseMenu(List<MenuItem> revisedMenu){
        throw new UnsupportedOperationException();
    }

    public Optional<MenuItem> findMenuItem(String menuItemId){
        return menuItems.stream().filter(menuItem -> menuItem.getId().equals(menuItemId)).findFirst();
    }


}
