package com.snackhub.infrastructure.configuration.usecases;

import com.snackhub.application.payments.create.CreateImageQrCodeUseCase;
import com.snackhub.domain.payments.FileQrCodeGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileQrCodeUseCaseConfig {

    private final FileQrCodeGateway fileQrCodeGateway;

    public FileQrCodeUseCaseConfig(final FileQrCodeGateway fileQrCodeGateway) {
        this.fileQrCodeGateway = fileQrCodeGateway;
    }

    @Bean
    public CreateImageQrCodeUseCase createImageQrCodeUseCase() {
        return new CreateImageQrCodeUseCase(fileQrCodeGateway);
    }
}
