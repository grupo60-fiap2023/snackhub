package com.snackhub.application.payments.create;

import com.snackhub.application.AuthUseCase;
import com.snackhub.application.payments.OrderQrCodeOutput;
import com.snackhub.domain.payments.OrderQrCode;
import com.snackhub.domain.payments.OrderQrCodeCashOut;
import com.snackhub.domain.payments.OrderQrCodeGateway;
import com.snackhub.domain.payments.OrderQrCodeItem;

import java.util.List;
import java.util.Objects;

public class CreateOrderQrCodeUseCase extends AuthUseCase<CreateOrderQrCodeCommand, OrderQrCodeOutput> {
    private final OrderQrCodeGateway orderQrCodeGateway;

    public CreateOrderQrCodeUseCase(final OrderQrCodeGateway orderQrCodeGateway) {
        this.orderQrCodeGateway = Objects.requireNonNull(orderQrCodeGateway);
    }

    @Override
    public OrderQrCodeOutput execute(String authorization, CreateOrderQrCodeCommand command, String userId, String externalPosId) {
        //TODO Ajustar para stream
        List<OrderQrCodeItem> items = List.of(OrderQrCodeItem.with(command.items().get(0).id(), command.items().get(0).skuNumber(),
                command.items().get(0).category(), command.items().get(0).title(), command.items().get(0).description(),
                command.items().get(0).unitPrice(), command.items().get(0).quantity(), command.items().get(0).unitMeasure(),
                command.items().get(0).totalAmount()));

        var cashOut = new OrderQrCodeCashOut(command.cashOut().amount());

        var orderQrCode = new OrderQrCode(command.externalReference(), command.title(), command.notificationUrl(),
                command.totalAmount(), items, cashOut, command.description());

        return OrderQrCodeOutput.from(this.orderQrCodeGateway.createOrderQRCode(authorization, orderQrCode, userId, externalPosId));
    }
}
