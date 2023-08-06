package com.snackhub.infrastructure.configuration.usecases;

import com.snackhub.application.customer.autenticate.AutenticateCustomerByCpfUseCase;
import com.snackhub.application.customer.create.CreateCustomerUseCase;
import com.snackhub.application.customer.retrieve.FindAllCustomersUseCase;
import com.snackhub.application.customer.retrieve.FindCustomerByCpfUseCase;
import com.snackhub.domain.customer.CustomerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerUseCaseConfig {

    private final CustomerGateway customerGateway;

    public CustomerUseCaseConfig(final CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Bean
    public CreateCustomerUseCase createCustomer() {
        return new CreateCustomerUseCase(customerGateway);
    }

    @Bean
    public FindAllCustomersUseCase findAllCustomers() {
        return new FindAllCustomersUseCase(customerGateway);
    }

    @Bean
    public FindCustomerByCpfUseCase findCustomerByCpfUseCase(){
        return new FindCustomerByCpfUseCase(customerGateway);
    }

    @Bean
    public AutenticateCustomerByCpfUseCase autenticateCustomerByCpfUseCase(){
        return new AutenticateCustomerByCpfUseCase(customerGateway);
    }
}
