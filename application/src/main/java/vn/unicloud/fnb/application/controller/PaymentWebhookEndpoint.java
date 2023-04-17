package vn.unicloud.fnb.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.unicloud.fnb.dto.POSCharged;

@RequestMapping("/api/1.0/webhook/")
@Tags(@Tag(name="Payment Webhook"))
public interface PaymentWebhookEndpoint {

    @PostMapping("/pos/charged")
    void onCharged(POSCharged chargedEvent);
}
