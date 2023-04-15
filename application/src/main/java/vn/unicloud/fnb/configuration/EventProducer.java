package vn.unicloud.fnb.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.GenericDomainEventMessage;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.dyson.core.model.DomainEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.DomainEvents;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EventProducer {
    private final EventGateway eventGateway;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @DomainEvents
    public void onDomainEvent(DomainEvent e) throws InterruptedException {
        log.debug("{}", e);
        Thread.sleep(1000);
        UnitOfWork<EventMessage<DomainEvent>> uow = DefaultUnitOfWork.startAndGet(GenericDomainEventMessage.asEventMessage(e));
        uow.execute(() -> {
            eventGateway.publish(GenericDomainEventMessage.asEventMessage(e));
        });
//        eventGateway.publish(e);
        log.debug("published {}", e);
    }

}

