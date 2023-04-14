package vn.unicloud.fnb.order;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import vn.unicloud.fnb.dto.*;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("amqpEvents")
public class OrderEventHandler {
    @QueryHandler
    public OrderDto on(OrderQuery query) {
        throw new UnsupportedOperationException();
    }
}
