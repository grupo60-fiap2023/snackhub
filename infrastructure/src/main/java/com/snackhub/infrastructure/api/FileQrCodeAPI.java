package com.snackhub.infrastructure.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "qr/server")
@Tag(name = "Image QR Code")
public interface FileQrCodeAPI {

    @GetMapping(value = "createImageQrCode",
            produces = MediaType.IMAGE_PNG_VALUE)
    @Operation(summary = "Create Image QR Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<byte[]> createImageQrCode(@RequestParam("data") @Schema(description = "qr_data", example = "00020101021243650016COM.MERCADOLIBRE0201306369fb91863-2b00-4333-92ae-a700114537225204000053039865802BR5912Marlon%20Costa6009SAO%20PAULO62070503%2A%2A%2A6304EBF6")
                                             String data);
}
