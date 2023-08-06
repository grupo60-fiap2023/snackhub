package com.snackhub.domain.customer;


import com.snackhub.domain.Entity;
import com.snackhub.domain.validation.ValidationHandler;

public class Customer extends Entity<CustomerId> {

    private final String firstName;
    private final String lastName;
    private final String cpf;

    private Customer(CustomerId customerId, String firstName, String lastName, String cpf) {
        super(customerId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public static Customer newCustomer(final String firstName, final String lastName, final String cpf) {
        final var id = CustomerId.unique();
        return new Customer(id, firstName, lastName, cpf);
    }

    public static Customer with(final CustomerId customerId, final String firstName, final String lastName, final String cpf) {
        return new Customer(customerId, firstName, lastName, cpf);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CustomerValidator(this, handler).validate();
    }
}
