package vn.unicloud.fnb.application.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.fnb.application.controller.PaymentWebhookEndpoint;
import vn.unicloud.fnb.dto.POSCharged;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentWebhookEndpointImpl implements PaymentWebhookEndpoint {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final EventGateway eventGateway;

    @Override
    public void onCharged(POSCharged chargedEvent) {
        eventGateway.publish(chargedEvent);
    }
}
