package org.ctb.restaurantservice.domain;

import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import org.ctb.common.CommonConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Map;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan
@Import({TramEventsPublisherConfiguration.class, CommonConfiguration.class})
public class RestaurantServiceDomainConfiguration {

    @Bean
    public RestaurantService restaurantService(RestaurantRepository restaurantRepository) {
        return new RestaurantService(restaurantRepository,restaurantDomainEventPublisher(new DomainEventPublisher() {
            @Override
            public void publish(String s, Object o, List<DomainEvent> list) {

            }

            @Override
            public void publish(String s, Object o, Map<String, String> map, List<DomainEvent> list) {

            }
        }));
    }

    @Bean
    public RestaurantDomainEventPublisher restaurantDomainEventPublisher(DomainEventPublisher domainEventPublisher){
        return new RestaurantDomainEventPublisher(domainEventPublisher);
    }
}
