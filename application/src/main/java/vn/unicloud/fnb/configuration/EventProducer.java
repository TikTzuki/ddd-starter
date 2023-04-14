package vn.unicloud.fnb.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.dyson.core.model.DomainEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EventProducer {
    private final EventGateway eventGateway;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(DomainEvent e) {
        log.debug("received event {}", e);
        eventGateway.publish(e);
        log.debug("event gateway pushed {}", e);
    }

}

