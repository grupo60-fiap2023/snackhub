package com.snackhub.infrastructure.customer;

import com.snackhub.domain.customer.Customer;
import com.snackhub.domain.customer.CustomerGateway;
import com.snackhub.domain.customer.CustomerId;
import com.snackhub.infrastructure.customer.persistence.CustomerJpaEntity;
import com.snackhub.infrastructure.customer.persistence.CustomerRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerMySQLGateway implements CustomerGateway {

    private final CustomerRepository repository;

    public CustomerMySQLGateway(final CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Customer> findCustomerByCpf(String cpf) {
        CustomerJpaEntity customerJpaEntity = CustomerJpaEntity.from(cpf);
        Example<CustomerJpaEntity> example = Example.of(customerJpaEntity);
        return this.repository.findOne(example).map(CustomerJpaEntity::toAggregate);
    }

    @Override
    public Customer save(Customer customer) {
        return this.repository.save(CustomerJpaEntity.from(customer)).toAggregate();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return this.repository.findAll().stream().map(CustomerJpaEntity::toAggregate).toList();
    }

    @Override
    public Optional<Customer> findCustomerById(CustomerId customerId) {
        return this.repository.findById(customerId.getValue()).map(CustomerJpaEntity::toAggregate);
    }
}
