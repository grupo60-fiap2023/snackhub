package com.snackhub.infrastructure.payments.feign.client;


import com.snackhub.infrastructure.configuration.FeignConfig;
import com.snackhub.infrastructure.payments.models.CreateOrderQrCodeRequest;
import com.snackhub.infrastructure.payments.models.OrderQrCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

//TODO interpolação uri
@FeignClient(value = "mp", url = "https://api.mercadopago.com", configuration = FeignConfig.class)
public interface MPIntegrationGateway {

    @PostMapping(value = "/instore/orders/qr/seller/collectors/{userId}/pos/{externalPosId}/qrs")
    ResponseEntity<OrderQrCodeResponse> createOrderQRCode(@RequestHeader("Authorization") String authorization,
                                                          @RequestBody CreateOrderQrCodeRequest request,
                                                          @PathVariable String userId,
                                                          @PathVariable String externalPosId);

}
