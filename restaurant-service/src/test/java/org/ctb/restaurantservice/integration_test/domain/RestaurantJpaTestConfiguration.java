package org.ctb.restaurantservice.integration_test.domain;

import io.eventuate.tram.spring.consumer.jdbc.TramConsumerJdbcAutoConfiguration;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration(exclude = TramConsumerJdbcAutoConfiguration.class)
@ComponentScan(basePackages = "org.ctb.restaurantservice.domain")
@Import(
        {
                TramMessageProducerJdbcConfiguration.class,
                TramEventsPublisherConfiguration.class
        }
)
public class RestaurantJpaTestConfiguration {
}
