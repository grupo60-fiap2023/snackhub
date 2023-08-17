package com.snackhub.application.payments.create;

public record CreateOrderQrCodeSponsorCommand(Integer id) {

    public static CreateOrderQrCodeSponsorCommand with(final Integer id) {
        return new CreateOrderQrCodeSponsorCommand(id);
    }
}
