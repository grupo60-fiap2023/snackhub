package com.snackhub.infrastructure.payments.presenters;

import com.snackhub.application.payments.OrderQrCodeOutput;
import com.snackhub.infrastructure.payments.models.OrderQrCodeResponse;

public interface OrderQrCodeApiPresenter {
    static OrderQrCodeResponse present(OrderQrCodeOutput orderQrCodeOutput) {
        return new OrderQrCodeResponse(orderQrCodeOutput.inStoreOrderId(), orderQrCodeOutput.qrData());
    }
}
