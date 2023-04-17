package vn.unicloud.fnb.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/webhook/")
@Tags(@Tag(name="Payment Webhook"))
public interface PaymentWebhookEndpoint {

}
