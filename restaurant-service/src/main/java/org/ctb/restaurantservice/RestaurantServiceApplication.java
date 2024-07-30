package org.ctb.restaurantservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eventuate.common.json.mapper.JSonMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import org.springframework.context.annotation.Primary;

@SpringBootApplication(scanBasePackages = "org.ctb.restaurantservice" )
@Import(TramJdbcKafkaConfiguration.class)
public class RestaurantServiceApplication {

	@Bean
	@Primary
	public ObjectMapper objectMapper(){
		return JSonMapper.objectMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}

}
