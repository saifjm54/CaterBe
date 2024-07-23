package org.ctb.restaurantservice.domain;

import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.ctb.restaurantservice.events.RestaurantDomainEvent;

public class RestaurantDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Restaurant, RestaurantDomainEvent> {
    public RestaurantDomainEventPublisher(DomainEventPublisher eventPublisher) {
        super(eventPublisher, Restaurant.class,Restaurant::getId);
    }
}
