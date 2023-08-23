package com.snackhub.application.payments.create;

import com.snackhub.application.UseCase;
import com.snackhub.domain.payments.FileQrCodeGateway;

public class CreateImageQrCodeUseCase extends UseCase<CreateImageQrCodeCommand, byte[]> {

    private final FileQrCodeGateway fileQrCodeGateway;

    public CreateImageQrCodeUseCase(FileQrCodeGateway fileQrCodeGateway) {
        this.fileQrCodeGateway = fileQrCodeGateway;
    }

    @Override
    public byte[] execute(CreateImageQrCodeCommand command) {
        return fileQrCodeGateway.createImageQRCode(command.data());
    }
}
