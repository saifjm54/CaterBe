package org.ctb.orderservice.domain;


import java.util.Collections;

public class OrderDetailsMother {

    public static long CONSUMER_ID = 1511300065921L;

    public static final int CHICKEN_VINDALOO_QUANTITY = 5;
    public static final MenuItemIdAndQuantity CHICKEN_VINDALOO_MENU_ITEM_AND_QUANTITY = new MenuItemIdAndQuantity(RestaurantMother.CHICKEN_VINDALOO_MENU_ITEM_ID, CHICKEN_VINDALOO_QUANTITY);
    public static final List<MenuItemIdAndQuantity> CHICKEN_VINDALOO_MENU_ITEMS_AND_QUANTITIES = Collections.singletonList(CHICKEN_VINDALOO_MENU_ITEM_AND_QUANTITY);

    public static List<OrderLineItem> chickenVindalooLineItems() {
        return Collections.singletonList(new OrderLineItem(CHICKEN_VINDALOO_MENU_ITEM_AND_QUANTITY.getMenuItemId(),
                CHICKEN_VINDALOO,
                CHICKEN_VINDALOO_PRICE,
                CHICKEN_VINDALOO_MENU_ITEM_AND_QUANTITY.getQuantity()));
    }

    public static final Money CHICKEN_VINDALOO_ORDER_TOTAL = CHICKEN_VINDALOO_PRICE.multiply(5);
    public static final OrderDetails CHICKEN_VINDALOO_ORDER_DETAILS = new OrderDetails(CONSUMER_ID, AJANTA_ID,
            chickenVindalooLineItems(), CHICKEN_VINDALOO_ORDER_TOTAL);

    public static long ORDER_ID = 99L;

    public static final OrderState CHICKEN_VINDALOO_ORDER_STATE = OrderState.APPROVAL_PENDING;

    public static final Address DELIVERY_ADDRESS = new Address("9 Amazing View", null, "Oakland", "CA", "94612");
    public static final LocalDateTime DELIVERY_TIME = LocalDateTime.now();
    public static final DeliveryInformation DELIVERY_INFORMATION = new DeliveryInformation(DELIVERY_TIME, DELIVERY_ADDRESS);

    private static Order makeAjantaOrder() {
        Order order = new Order(CONSUMER_ID, AJANTA_ID, new DeliveryInformation(DELIVERY_TIME, DELIVERY_ADDRESS), chickenVindalooLineItems());
        order.setId(ORDER_ID);
        return order;
    }

    public static Order CHICKEN_VINDALOO_ORDER = makeAjantaOrder();

}
