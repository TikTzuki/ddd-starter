package vn.unicloud.fnb.order;

import lombok.Data;
import org.dyson.core.model.DomainEvent;

import java.io.Serializable;

@Data
public class OrderShipped implements DomainEvent, Serializable {
    private final Long orderId;
    private final Long occurredOn;

    public OrderShipped(Long order, Long occurredOn) {
        this.orderId = order;
        this.occurredOn = occurredOn;
    }

}
