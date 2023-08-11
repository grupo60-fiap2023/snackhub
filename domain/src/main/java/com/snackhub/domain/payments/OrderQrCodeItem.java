 package com.snackhub.domain.payments;


public record OrderQrCodeItem(String id, String skuNumber, String category, String title, String description,
                              Double unitPrice, Integer quantity, String unitMeasure, Double totalAmount) {

    public static OrderQrCodeItem with(final String id,
                                       final String skuNumber,
                                       final String category,
                                       final String title,
                                       final String description,
                                       final Double unitPrice,
                                       final Integer quantity,
                                       final String unitMeasure,
                                       final Double totalAmount) {
        return new OrderQrCodeItem(
                id,
                skuNumber,
                category,
                title,
                description,
                unitPrice,
                quantity,
                unitMeasure,
                totalAmount);
    }


}
