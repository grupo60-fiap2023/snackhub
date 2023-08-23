package com.snackhub.domain.payments;

public interface FileQrCodeGateway {

    byte[] createImageQRCode(String data);
}
