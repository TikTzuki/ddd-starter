package org.tiktzuki.dddstater.domains.model.order;

import org.ddd.core.model.DomainEvent;

import java.time.Instant;

public class OrderShipped implements DomainEvent {
    private final Long orderId;
    private final Instant occurredOn;

    public OrderShipped(Long order, Instant occurredOn) {
        this.orderId = order;
        this.occurredOn = occurredOn;
    }

}
