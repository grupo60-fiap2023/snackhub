package com.snackhub.domain.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerGateway {

    Optional<Customer> findCustomerByCpf(String cpf);

    Customer save(Customer customer);

    List<Customer> findAllCustomers();

    Optional<Customer> findCustomerById(CustomerId customerId);
}
