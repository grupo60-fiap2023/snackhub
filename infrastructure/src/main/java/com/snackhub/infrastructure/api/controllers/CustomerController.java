package com.snackhub.infrastructure.api.controllers;

import com.snackhub.application.customer.CustomerOutput;
import com.snackhub.application.customer.autenticate.AutenticateCustomerByCpfUseCase;
import com.snackhub.application.customer.create.CreateCustomerCommand;
import com.snackhub.application.customer.create.CreateCustomerUseCase;
import com.snackhub.application.customer.retrieve.FindAllCustomersUseCase;
import com.snackhub.application.customer.retrieve.FindCustomerByCpfUseCase;
import com.snackhub.domain.exceptions.DomainException;
import com.snackhub.infrastructure.api.CustomerAPI;
import com.snackhub.infrastructure.customer.models.AutenticateRequest;
import com.snackhub.infrastructure.customer.models.CustomerRequest;
import com.snackhub.infrastructure.customer.models.CustomerResponse;
import com.snackhub.infrastructure.customer.presenters.CustomerApiPresenter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUserCase;
    private final FindAllCustomersUseCase findAllCustomersUseCase;
    private final FindCustomerByCpfUseCase findCustomerByCpfUseCase;
    private final AutenticateCustomerByCpfUseCase autenticateCustomerByCpfUseCase;

    public CustomerController(
            final CreateCustomerUseCase createCustomerUserCase,
            final FindAllCustomersUseCase findAllCustomersUseCase,
            final FindCustomerByCpfUseCase findCustomerByCpfUseCase,
            final AutenticateCustomerByCpfUseCase autenticateCustomerByCpfUseCase) {
        this.createCustomerUserCase = createCustomerUserCase;
        this.findAllCustomersUseCase = findAllCustomersUseCase;
        this.findCustomerByCpfUseCase = findCustomerByCpfUseCase;
        this.autenticateCustomerByCpfUseCase = autenticateCustomerByCpfUseCase;
    }

    @Override
    public ResponseEntity<?> createCustomer(CustomerRequest request) {
        final CustomerOutput customerInBase = this.findCustomerByCpfUseCase.execute(request.cpfNumber());
        try{
            if (customerInBase == null){
                var command = CreateCustomerCommand.with(request.firstName(), request.lastName(), request.cpfNumber());
                var customer = this.createCustomerUserCase.execute(command);
                CustomerResponse output = CustomerApiPresenter.present(customer);
                return ResponseEntity.created(URI.create("/customer" + output.id())).body(output);

            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(customerInBase);
            }
        }catch (DomainException e){
            return ResponseEntity.unprocessableEntity().body(e.getErrors());
        }
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> listAllCustomers() {
        return ResponseEntity.ok().body(this.findAllCustomersUseCase.execute().stream().map(CustomerApiPresenter::present).toList());
    }

    @Override
    public ResponseEntity<?> autenticateCustomer(@Valid AutenticateRequest request) {
        try{
            final CustomerOutput customer = this.autenticateCustomerByCpfUseCase.execute(request.getCpfNumber());
            return ResponseEntity.ok().body(CustomerApiPresenter.present(customer));
        }catch (DomainException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getErrors());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body(message);
    }
}
