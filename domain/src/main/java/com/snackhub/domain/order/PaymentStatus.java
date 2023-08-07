package com.snackhub.domain.order;

public enum PaymentStatus {

    APPROVED(1,"Aprovado"),
    DENIED(2, "Negado");

    private final Integer id;
    private final String name;

    PaymentStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
