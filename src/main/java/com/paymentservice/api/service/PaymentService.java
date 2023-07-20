package com.paymentservice.api.service;

import com.paymentservice.api.entity.PaymentTransaction;
import com.paymentservice.api.payload.PaymentPayload;
import com.paymentservice.api.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    @Autowired
    private PaymentRepository paymentRepository;
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public String createPayment(PaymentPayload paymentPayload) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (paymentPayload.getAmount() * 100)); // Stripe expects amount in cents
        params.put("currency", "usd");
        params.put("description", paymentPayload.getDescription());

        PaymentIntent paymentIntent = PaymentIntent.create(params);



        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setAmount(paymentPayload.getAmount());
        paymentTransaction.setDescription(paymentPayload.getDescription());
        paymentRepository.save(paymentTransaction);
        return paymentIntent.getId();
    }
}
