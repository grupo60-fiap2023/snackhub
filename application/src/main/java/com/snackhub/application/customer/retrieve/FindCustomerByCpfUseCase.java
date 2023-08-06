package com.snackhub.application.customer.retrieve;

import com.snackhub.application.UseCase;
import com.snackhub.application.customer.CustomerOutput;
import com.snackhub.domain.customer.Customer;
import com.snackhub.domain.customer.CustomerGateway;

import java.util.Objects;
import java.util.Optional;

public class FindCustomerByCpfUseCase extends UseCase<String,CustomerOutput > {

    private final CustomerGateway customerGateway;

    public FindCustomerByCpfUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);;
    }

    @Override
    public CustomerOutput execute(String cpf) {
        Optional<Customer> customerByCpf = this.customerGateway.findCustomerByCpf(cpf);

        if(customerByCpf.isPresent()){
            return CustomerOutput.from(customerByCpf.get());
        }
        return null;
    }
}
