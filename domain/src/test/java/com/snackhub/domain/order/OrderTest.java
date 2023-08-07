package com.snackhub.domain.order;


import com.snackhub.domain.customer.Customer;
import com.snackhub.domain.product.Category;
import com.snackhub.domain.product.Product;
import com.snackhub.domain.validation.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderTest {

    private Product getProductMock() {
        final var expectedName = "Big Mac";
        final var expectedDescription = "Dois hambúrgueres (100% carne bovina), alface americana, queijo sabor cheddar, molho especial, cebola, picles e pão com gergelim.";
        final var expectedPrice = BigDecimal.valueOf(25.0);
        final var expectedCategory = Category.SNACK;

        return Product.newProduct(expectedName, expectedPrice, expectedDescription, expectedCategory);
    }

    private Customer getCustomerMock(){
        return Customer.newCustomer("Jose", "Silva", "09012312312");
    }

    @Test
    public void givenAValidParams_whenCallNewOrder_thenInstantiateAOrder() {
        OrderItem item = OrderItem.newOrderItem(getProductMock(), 2);
        final var order = Order.newOrder(Arrays.asList(item), getCustomerMock(), null, PaymentStatus.APPROVED);

        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getId());
        Assertions.assertNotNull(order.getCustomer());
        Assertions.assertEquals(item, order.getItems().stream().findFirst().get());
    }

    @Test
    public void givenAInvalidObservation_whenCallNewOrdertAndValidate_thenShouldReceiveError() {
        final String expectedObservation = """
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                """;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'observation' must be between 1 and 255 characters";

        OrderItem item = OrderItem.newOrderItem(getProductMock(), 2);
        final var order = Order.newOrder(Arrays.asList(item), getCustomerMock(), expectedObservation, PaymentStatus.APPROVED);

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidOrderItems_whenCallNewOrdertAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "The order is necessary almost one item";

        final var order = Order.newOrder(null, getCustomerMock(), null, PaymentStatus.APPROVED);

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidEmptyOrderItems_whenCallNewOrdertAndValidate_thenShouldReceiveError() {
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "The order is necessary almost one item";

        final var order = Order.newOrder(new ArrayList<>(), getCustomerMock(), null, PaymentStatus.APPROVED);

        Notification notification = Notification.create();
        order.validate(notification);

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }
}
