 package com.snackhub.infrastructure.payments.presenters;

import com.snackhub.application.payments.OrderQrCodeOutput;
import com.snackhub.application.payments.create.CreateOrderQrCodeItemCommand;
import com.snackhub.infrastructure.payments.models.OrderQrCodeItemsRequest;
import com.snackhub.infrastructure.payments.models.OrderQrCodeResponse;

import java.util.List;

public interface OrderQrCodeItemApiPresenter {

    static List<CreateOrderQrCodeItemCommand> present(List<OrderQrCodeItemsRequest> itemsRequest) {
        return itemsRequest.stream().map(
                        itemRequest -> CreateOrderQrCodeItemCommand.with(
                                itemRequest.id(),
                                itemRequest.skuNumber(),
                                itemRequest.category(),
                                itemRequest.title(),
                                itemRequest.description(),
                                itemRequest.unitPrice(),
                                itemRequest.quantity(),
                                itemRequest.unitMeasure(),
                                itemRequest.totalAmount()
                        ))
                .toList();
    }

    static OrderQrCodeResponse present(final OrderQrCodeOutput orderItem) {
        return new OrderQrCodeResponse(
                orderItem.inStoreOrderId(),
                orderItem.qrData());
    }
}
