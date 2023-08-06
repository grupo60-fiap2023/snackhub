package com.snackhub.infrastructure.order.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.snackhub.domain.order.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record UpdateStatusRequest(@JsonProperty("status") @NotNull @Schema(description = "Estado do pedido", example = "RECEIVED, IN_PREPARATION, READY ou FINISHED") OrderStatus status) {
}
