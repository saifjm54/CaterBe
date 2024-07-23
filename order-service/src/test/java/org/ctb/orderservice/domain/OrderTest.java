package org.ctb.orderservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {

    private ResultWithDomainEvents<Order,OrderDomainEvent> createResult;
    private Order order;

    @BeforeEach
    public void setup() {
        createResult = Order.createOrder(CONSUMER_ID,AJANTA_RESTAURANT,OrderDetailsMother.DELIVERY_INFORMATION,chickenVindalooLineItems());
        order = createResult.result;
    }



}
