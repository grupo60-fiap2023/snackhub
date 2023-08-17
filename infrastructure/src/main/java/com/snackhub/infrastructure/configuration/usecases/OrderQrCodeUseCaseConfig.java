package com.snackhub.infrastructure.configuration.usecases;

import com.snackhub.application.payments.create.CreateOrderQrCodeUseCase;
import com.snackhub.domain.payments.OrderQrCodeGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderQrCodeUseCaseConfig {

    private final OrderQrCodeGateway orderQrCodeGateway;

    public OrderQrCodeUseCaseConfig(final OrderQrCodeGateway orderQrCodeGateway) {
        this.orderQrCodeGateway = orderQrCodeGateway;
    }

    @Bean
    public CreateOrderQrCodeUseCase createOrderQrCodeUseCase() {
        return new CreateOrderQrCodeUseCase(orderQrCodeGateway);
    }
}
