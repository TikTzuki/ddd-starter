package org.tiktzuki.dddstater.domains.order.model;

import lombok.Data;
import org.ddd.core.model.DomainEvent;

import java.io.Serializable;
import java.time.Instant;

@Data
public class OrderShipped implements DomainEvent, Serializable {
    private final Long orderId;
    private final Long occurredOn;

    public OrderShipped(Long order, Long occurredOn) {
        this.orderId = order;
        this.occurredOn = occurredOn;
    }

}
