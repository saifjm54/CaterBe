package org.ctb.restaurantservice.web;


import org.ctb.restaurantservice.domain.CreateRestaurantRequest;
import org.ctb.restaurantservice.domain.Restaurant;
import org.ctb.restaurantservice.domain.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;


    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public CreateRestaurantResponse create(@RequestBody CreateRestaurantRequest request){
        Restaurant restaurant = restaurantService.create(request);
        return new CreateRestaurantResponse(restaurant.getId());
    }

    @GetMapping(path = "/{restaurantId}")
    public ResponseEntity<GetRestaurantResponse> get(@PathVariable long restaurantId){

        return restaurantService.findById(restaurantId)
                .map(r -> new ResponseEntity<>(makeGetRestaurantResponse(r), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public GetRestaurantResponse makeGetRestaurantResponse(Restaurant restaurant){
        return new GetRestaurantResponse(restaurant.getId(), restaurant.getName());
    }

}
