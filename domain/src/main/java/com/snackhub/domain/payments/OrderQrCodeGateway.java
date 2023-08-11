package com.snackhub.domain.payments;

public interface OrderQrCodeGateway {

    OrderQrCodeOut createOrderQRCode(String authorization, OrderQrCode request, String userId, String externalPosId);
}
