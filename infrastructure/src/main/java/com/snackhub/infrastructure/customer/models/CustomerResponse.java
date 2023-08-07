package com.snackhub.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("cpf") String cpfNumber) {
}
