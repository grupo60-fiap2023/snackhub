package com.snackhub.infrastructure.order.presenters;

import com.snackhub.application.order.OrderItemOutput;
import com.snackhub.application.order.create.CreateOrderItemCommand;
import com.snackhub.infrastructure.order.models.OrderItemRequest;
import com.snackhub.infrastructure.order.models.OrderItemResponse;

import java.util.List;

public interface OrderItemApiPresenter {

    static List<CreateOrderItemCommand> present(List<OrderItemRequest> itemsRequest) {
        return itemsRequest.stream().map(
                itemRequest -> CreateOrderItemCommand.with(itemRequest.productId(), itemRequest.quantity()))
                .toList();
    }

    static OrderItemResponse present(final OrderItemOutput orderItem) {
        return new OrderItemResponse(orderItem.id(),
                orderItem.productId(),
                orderItem.productName(),
                orderItem.quantity());
    }
}
