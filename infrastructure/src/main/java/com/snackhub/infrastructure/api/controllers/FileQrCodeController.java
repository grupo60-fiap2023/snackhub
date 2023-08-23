package com.snackhub.infrastructure.api.controllers;

import com.snackhub.application.payments.create.CreateImageQrCodeCommand;
import com.snackhub.application.payments.create.CreateImageQrCodeUseCase;
import com.snackhub.infrastructure.api.FileQrCodeAPI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileQrCodeController implements FileQrCodeAPI {

    private final CreateImageQrCodeUseCase createImageQrCodeUseCase;

    public FileQrCodeController(CreateImageQrCodeUseCase createImageQrCodeUseCase) {
        this.createImageQrCodeUseCase = createImageQrCodeUseCase;
    }


    @Override
    public ResponseEntity<byte[]> createImageQrCode(String data) {

        final byte[] image = createImageQrCodeUseCase.execute(CreateImageQrCodeCommand.with(data));

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
