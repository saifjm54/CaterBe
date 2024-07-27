package org.ctb.restaurantservice.web;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ctb.restaurantservice.RestaurantDetailsMother;
import org.ctb.restaurantservice.domain.CreateRestaurantRequest;
import org.ctb.restaurantservice.domain.Restaurant;
import org.ctb.restaurantservice.domain.RestaurantMenu;
import org.ctb.restaurantservice.domain.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.awaitility.Awaitility.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestaurantControllerTest {

    private RestaurantService restaurantService;
    private RestaurantController restaurantController;

    protected MockMvc mockMvc;

    public static String uri = "/restaurants";

    public static RestaurantMenu menu;

    @BeforeEach
    public void setup() {
        restaurantService = mock(RestaurantService.class);
        restaurantController = new RestaurantController(restaurantService);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void shouldCreateAndReturnRestaurantResponseWith_200_HTTP_STATUS() throws Exception {
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        request.setName(RestaurantDetailsMother.AJANTA_RESTAURANT_NAME);
        request.setMenu(RestaurantDetailsMother.getRestaurantMenu());
        request.setAddress(RestaurantDetailsMother.RESTAURANT_ADDRESS);

        String inputJson = mapToJson(request);

        CreateRestaurantResponse response = new CreateRestaurantResponse(RestaurantDetailsMother.AJANTA_ID);


        when(
                restaurantService.create(request)
        ).thenReturn(new Restaurant(RestaurantDetailsMother.AJANTA_ID,RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,RestaurantDetailsMother.getRestaurantMenu()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(
                MediaType.APPLICATION_JSON_VALUE
        ).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

        String content = mvcResult.getResponse().getContentAsString();
        String actualResult = mapToJson(response);
        assertEquals(content,actualResult);

    }

    @Test
    public void shouldReturnGetRestaurantResponseByIdWith_200_HTTP_STATUS() throws Exception {

        when(
                restaurantService.findById(RestaurantDetailsMother.AJANTA_ID)
        ).thenReturn(Optional.of(new Restaurant(RestaurantDetailsMother.AJANTA_ID,RestaurantDetailsMother.AJANTA_RESTAURANT_NAME,RestaurantDetailsMother.getRestaurantMenu())));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri+"/{id}", RestaurantDetailsMother.AJANTA_ID)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();

        GetRestaurantResponse expectedResopne = new GetRestaurantResponse(RestaurantDetailsMother.AJANTA_ID,RestaurantDetailsMother.AJANTA_RESTAURANT_NAME);
        String expected = mapToJson(expectedResopne);
        assertEquals(expected,content);
    }

    @Test
    public void shouldReturnNotFoundException_404_HTTP_STATUS_When_Restaurant_Does_Not_Exisits() throws Exception {
        when(
                restaurantService.findById(RestaurantDetailsMother.AJANTA_ID)
        ).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri+"/{id}", RestaurantDetailsMother.AJANTA_ID)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404,status);



    }



}
