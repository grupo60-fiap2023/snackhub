package com.snackhub.infrastructure.payments.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record OrderQrCodeResponse(
        @JsonProperty("in_store_order_id") @Schema(description = "id da Order QrCode", example = "9fb91863-2b00-4333-92ae-a70011453722")
        String inStoreOrderId,
        @JsonProperty("qr_data") @Schema(description = "Dados da imagem QrCode", example = "00020101021243650016COM.MERCADOLIBRE0201306369fb91863-2b00-4333-92ae-a700114537225204000053039865802BR5912Marlon Costa6009SAO PAULO62070503***6304EBF6")
        String qrData) {
}
