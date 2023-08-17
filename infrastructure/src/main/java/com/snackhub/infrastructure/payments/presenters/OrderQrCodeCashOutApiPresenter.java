package com.snackhub.infrastructure.payments.presenters;

import com.snackhub.application.payments.create.CreateOrderQrCodeCashOutCommand;
import com.snackhub.infrastructure.payments.models.OrderQrCodeCashOutRequest;

public interface OrderQrCodeCashOutApiPresenter {
    static CreateOrderQrCodeCashOutCommand present(OrderQrCodeCashOutRequest request) {
        return new CreateOrderQrCodeCashOutCommand(request.amount());
    }
}
