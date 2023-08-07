package com.snackhub.infrastructure.customer.persistence;

import com.snackhub.domain.customer.Customer;
import com.snackhub.domain.customer.CustomerId;

import javax.persistence.*;

@Entity(name = "Customer")
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    public CustomerJpaEntity() {
    }

    public CustomerJpaEntity(String firstName, String lastName, String cpf) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public CustomerJpaEntity(Long id, String firstName, String lastName, String cpf) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public static CustomerJpaEntity create(final Customer customer) {
        return new CustomerJpaEntity(customer.getFirstName(), customer.getLastName(), customer.getCpf());
    }

    public static CustomerJpaEntity from(final Customer customer) {
        return new CustomerJpaEntity(customer.getId().getValue(), customer.getFirstName(), customer.getLastName(), customer.getCpf());
    }

    public static CustomerJpaEntity from(final String cpf) {
        return new CustomerJpaEntity(null, null, cpf);
    }

    public Customer toAggregate() {
        return Customer.with(CustomerId.from(getId()), getFirstName(), getLastName(), getCpf());
    }

    public Long getId() {
        return id;
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
}
