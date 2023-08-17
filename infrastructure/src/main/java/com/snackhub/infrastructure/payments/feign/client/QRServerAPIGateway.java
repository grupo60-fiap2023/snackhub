package com.snackhub.infrastructure.payments.feign.client;


import com.snackhub.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//TODO interpolação uri
@FeignClient(value = "qr", url = "https://api.qrserver.com/v1/create-qr-code", configuration = FeignConfig.class)
public interface QRServerAPIGateway {

    @GetMapping(value = "/", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    ResponseEntity<byte[]> createImageQRCode(@RequestParam("data") String data);
}
