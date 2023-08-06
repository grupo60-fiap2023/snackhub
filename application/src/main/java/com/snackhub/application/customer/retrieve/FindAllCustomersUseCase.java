package com.snackhub.application.customer.retrieve;


import com.snackhub.application.NullaryUseCase;
import com.snackhub.application.customer.CustomerOutput;
import com.snackhub.domain.customer.CustomerGateway;

import java.util.List;
import java.util.Objects;

public class FindAllCustomersUseCase extends NullaryUseCase<List<CustomerOutput>> {

    private final CustomerGateway customerGateway;

    public FindAllCustomersUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);;
    }

    @Override
    public List<CustomerOutput> execute() {
        return this.customerGateway.findAllCustomers().stream().map(CustomerOutput::from).toList();
    }
}
