package com.snackhub.domain.customer;


import com.snackhub.domain.validation.Error;
import com.snackhub.domain.validation.ValidationHandler;
import com.snackhub.domain.validation.Validator;

public class CustomerValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    private final Customer customer;

    public CustomerValidator(final Customer customer, final ValidationHandler aHandler) {
        super(aHandler);
        this.customer = customer;
    }

    @Override
    public void validate() {
        String firstName = this.customer.getFirstName();
        checkConstraints("firstName", firstName);
        String lastName = this.customer.getLastName();
        checkConstraints("lastName", lastName);

        String cpfNumber = this.customer.getCpf();
        checkCPF(cpfNumber);
    }

    private void checkCPF(String value) {
        if (value == null) {
            this.validationHandler().append(new Error("'CPF' should not be null"));
            return;
        }

        if (value.isBlank()) {
            this.validationHandler().append(new Error("'CPF' should not be empty"));
            return;
        }

        boolean isNumeric = value.matches("[0-9]+");
        if (!isNumeric) {
            this.validationHandler().append(new Error("'CPF' should be numeric"));
        }
    }

    private void checkConstraints(String fieldName, String value) {
        if (value == null) {
            this.validationHandler().append(new Error("'"+ fieldName +"' should not be null"));
            return;
        }

        if (value.isBlank()) {
            this.validationHandler().append(new Error("'"+ fieldName +"' should not be empty"));
            return;
        }

        final int length = value.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'"+ fieldName +"' must be between 3 and 255 characters"));
        }
    }
}
