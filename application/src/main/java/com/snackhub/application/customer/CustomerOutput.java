package com.snackhub.application.customer;


import com.snackhub.domain.customer.Customer;

public record CustomerOutput(String id,
                             String firstName,
                             String lastName,
                             String cpf) {

    public static CustomerOutput from(final Customer customer) {
        return new CustomerOutput(customer.getId().getValue(),
                customer.getFirstName(), customer.getLastName(), customer.getCpf());
    }
}
