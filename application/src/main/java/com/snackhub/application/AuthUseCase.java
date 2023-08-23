package com.snackhub.application;

public abstract class AuthUseCase<IN, OUT> {

    public abstract OUT execute(String token, IN inputObject, String userId, String externalPosId);
}
