package com.example.Gym.Services;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.math.BigDecimal;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    public String createPayment(String title, Double amount) throws MPException, MPApiException {
        // Initialize Mercado Pago
        MercadoPagoConfig.setAccessToken(accessToken);

        // Create item details
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .title(title)
                .quantity(1)
                .unitPrice(BigDecimal.valueOf(amount))
                .currencyId("MXN") // Change currency if needed
                .build();

        // Set Buyer Information (Test Buyer Account)
        PreferencePayerRequest payerRequest = PreferencePayerRequest.builder()
                .email("TESTUSER2026771381@example.com") // ✅ Use your test buyer email
                .build();

        // Define back URLs for redirection after payment
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://www.your-site.com/success") // ✅ Redirect on success
                .pending("https://www.your-site.com/pending") // ✅ Redirect on pending
                .failure("https://www.your-site.com/failure") // ✅ Redirect on failure
                .build();

        // Create payment preference with back URLs
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(Collections.singletonList(itemRequest))
                .payer(payerRequest)
                .backUrls(backUrls)  // ✅ Include back URLs here
                .autoReturn("approved") // ✅ Auto redirect after successful payment
                .build();

        // Send request to Mercado Pago
        PreferenceClient client = new PreferenceClient();
        Preference response = client.create(preferenceRequest);

        return response.getInitPoint(); // ✅ Return Mercado Pago payment URL
    }
}
