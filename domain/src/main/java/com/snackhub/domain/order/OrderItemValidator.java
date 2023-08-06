package com.snackhub.domain.order;


import com.snackhub.domain.validation.Error;
import com.snackhub.domain.validation.ValidationHandler;
import com.snackhub.domain.validation.Validator;

public class OrderItemValidator extends Validator {

    private final OrderItem item;

    protected OrderItemValidator(final OrderItem item, final ValidationHandler aHandler) {
        super(aHandler);
        this.item = item;
    }

    @Override
    public void validate() {
        checkQuantityConstraints(item);
    }

    private void checkQuantityConstraints(OrderItem item) {
        Integer quantity = item.getQuantity();
        if (quantity != null && quantity.compareTo(0) < 1) {
            this.validationHandler().append(new Error("'quantity' must be greater than zero"));
        }
    }
}
