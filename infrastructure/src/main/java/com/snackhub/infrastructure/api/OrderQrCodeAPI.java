package com.snackhub.infrastructure.api;

import com.snackhub.infrastructure.payments.models.CreateOrderQrCodeRequest;
import com.snackhub.infrastructure.payments.models.OrderQrCodeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "mp/orders")
@Tag(name = "Payment QR Code")
public interface OrderQrCodeAPI {

    @PostMapping(value = "createOrderQrCode/{userId}/{externalPosId}/qrs",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new order QR Code", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<OrderQrCodeResponse> createOrderQrCode(
            @RequestHeader(required = false) @Schema(hidden = true) String authorization,
            @RequestBody @Valid CreateOrderQrCodeRequest input,
            @PathVariable @Schema(description = "Id do usuário da aplicação", example = "187206752") String userId,
            @PathVariable @Schema(description = "Id da Loja(POS) vincalado a aplicação", example = "SNACKBARPOS01") String externalPosId);
}
