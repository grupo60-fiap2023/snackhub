package com.snackhub.application.customer.autenticate;

import com.snackhub.application.UseCase;
import com.snackhub.application.customer.CustomerOutput;
import com.snackhub.domain.customer.Customer;
import com.snackhub.domain.customer.CustomerGateway;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.exceptions.ErrorName;
import com.snackhub.domain.validation.Error;

import java.util.Objects;
import java.util.Optional;

public class AutenticateCustomerByCpfUseCase extends UseCase<String, CustomerOutput> {

    private final CustomerGateway customerGateway;

    public AutenticateCustomerByCpfUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CustomerOutput execute(String cpf) {
        Optional<Customer> customer = this.customerGateway.findCustomerByCpf(cpf);
        if(customer.isEmpty()){
            throw DomainException.with(new Error(ErrorName.CUSTOMER_WITHOUT_ACESS));
        }
        return CustomerOutput.from(customer.get());
    }
}
