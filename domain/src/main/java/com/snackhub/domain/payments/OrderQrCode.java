package com.snackhub.domain.payments;

import java.util.List;

public class OrderQrCode{

    private String externalReference;
    private String title;
    private String notificationUrl;
    private Double totalAmount;
    private List<OrderQrCodeItem> items;
    private OrderQrCodeCashOut cashOut;
    private String description;

    public OrderQrCode(final String externalReference,
                       final String title,
                       final String notificationUrl,
                       final Double totalAmount,
                       final List<OrderQrCodeItem> items,
                       final OrderQrCodeCashOut cashOut,
                       final String description) {
        this.externalReference = externalReference;
        this.title = title;
        this.notificationUrl = notificationUrl;
        this.totalAmount = totalAmount;
        this.items = items;
        this.cashOut = cashOut;
        this.description = description;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderQrCodeItem> getItems() {
        return items;
    }

    public void setItems(List<OrderQrCodeItem> items) {
        this.items = items;
    }

    public OrderQrCodeCashOut getCashOut() {
        return cashOut;
    }

    public void setCashOut(OrderQrCodeCashOut cashOut) {
        this.cashOut = cashOut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
