package com.paymentservice.api.controller;

import com.paymentservice.api.payload.PaymentPayload;
import com.paymentservice.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
  // http://localhost:8080/payments
    // http://localhost:8080/payments
  // http://localhost:8080/payments
    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody PaymentPayload paymentPayload) {
        try {
            String paymentId = paymentService.createPayment(paymentPayload);
            return ResponseEntity.ok(paymentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
