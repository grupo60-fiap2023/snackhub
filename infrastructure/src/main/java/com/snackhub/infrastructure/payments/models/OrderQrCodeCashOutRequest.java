package com.snackhub.infrastructure.payments.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record OrderQrCodeCashOutRequest(
        @JsonProperty("amount") @NotNull @Schema(description = "amount", example = "0") Double amount) {

}
