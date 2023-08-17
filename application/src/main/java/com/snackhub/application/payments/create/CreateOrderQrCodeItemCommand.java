package com.snackhub.application.payments.create;

public record CreateOrderQrCodeItemCommand(

        String id,
        String skuNumber,
        String category,
        String title,
        String description,
        Double unitPrice,
        Integer quantity,
        String unitMeasure,
        Double totalAmount) {

    public static CreateOrderQrCodeItemCommand with(
            final String id,
            final String skuNumber,
            final String category,
            final String title,
            final String description,
            final Double unitPrice,
            final Integer quantity,
            final String unitMeasure,
            final Double totalAmount
    ) {
        return new CreateOrderQrCodeItemCommand(
                id,
                skuNumber,
                category,
                title,
                description,
                unitPrice,
                quantity,
                unitMeasure,
                totalAmount
        );
    }
}
