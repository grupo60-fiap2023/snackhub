package com.snackhub.infrastructure.customer.presenters;

import com.snackhub.application.customer.CustomerOutput;
import com.snackhub.infrastructure.customer.models.CustomerResponse;

public interface CustomerApiPresenter {

    static CustomerResponse present(final CustomerOutput customerOutput) {
        return new CustomerResponse(customerOutput.id(),
                customerOutput.firstName(), customerOutput.lastName(), customerOutput.cpf());
    }
}
