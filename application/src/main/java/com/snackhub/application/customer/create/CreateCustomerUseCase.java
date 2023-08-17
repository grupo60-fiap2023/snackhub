package com.snackhub.application.customer.create;

import com.snackhub.application.UseCase;
import com.snackhub.application.customer.CustomerOutput;
import com.snackhub.domain.customer.Customer;
import com.snackhub.domain.customer.CustomerGateway;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.domain.validation.Notification;

import java.util.Objects;

public class CreateCustomerUseCase extends UseCase<CreateCustomerCommand, CustomerOutput> {

    private final CustomerGateway customerGateway;

    public CreateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CustomerOutput execute(final CreateCustomerCommand command) {
        String cpfDigitOnly = command.cpf().replaceAll("\\D", "");
        Customer newCustomer = Customer.newCustomer(command.firstName(), command.lastName(), cpfDigitOnly);

        final var notification = Notification.create();
        newCustomer.validate(notification);
        if(notification.hasError()){
            throw DomainException.with(notification.getErrors());
        }

        Customer customer = this.customerGateway.save(newCustomer);
        return CustomerOutput.from(customer);
    }
}

