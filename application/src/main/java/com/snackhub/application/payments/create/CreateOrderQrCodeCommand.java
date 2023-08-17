package com.snackhub.application.payments.create;

import java.util.List;

public record CreateOrderQrCodeCommand(
        String externalReference,
        String title,
        String notificationUrl,
        Double totalAmount,
        List<CreateOrderQrCodeItemCommand> items,
        CreateOrderQrCodeCashOutCommand cashOut,
        String description
) {

    public static CreateOrderQrCodeCommand with(
            final String externalReference,
            final String title,
            final String notificationUrl,
            final Double totalAmount,
            final List<CreateOrderQrCodeItemCommand> items,
            final CreateOrderQrCodeCashOutCommand cashOut,
            final String description
    ) {
        return new CreateOrderQrCodeCommand(
                externalReference,
                title,
                notificationUrl,
                totalAmount,
                items,
                cashOut,
                description);
    }
}
