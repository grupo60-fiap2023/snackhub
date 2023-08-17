package com.snackhub.application.payments.create;

public record CreateImageQrCodeCommand(String data) {

    public static CreateImageQrCodeCommand with(final String data) {
        return new CreateImageQrCodeCommand(data);
    }
}
