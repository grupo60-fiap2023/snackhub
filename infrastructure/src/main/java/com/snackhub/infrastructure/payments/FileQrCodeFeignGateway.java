package com.snackhub.infrastructure.payments;

import com.snackhub.domain.payments.FileQrCodeGateway;
import com.snackhub.infrastructure.payments.feign.client.QRServerAPIGateway;
import org.springframework.stereotype.Component;

@Component
public class FileQrCodeFeignGateway implements FileQrCodeGateway {

    private final QRServerAPIGateway qrServerAPIGateway;

    public FileQrCodeFeignGateway(QRServerAPIGateway qrServerAPIGateway) {
        this.qrServerAPIGateway = qrServerAPIGateway;
    }


    @Override
    public byte[] createImageQRCode(String data) {
       return qrServerAPIGateway.createImageQRCode(data).getBody();
    }
}
