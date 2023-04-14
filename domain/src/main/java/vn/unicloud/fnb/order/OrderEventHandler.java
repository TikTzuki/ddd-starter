package vn.unicloud.fnb.order;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import vn.unicloud.fnb.dto.OrderDto;
import vn.unicloud.fnb.dto.OrderQuery;

@Component
@ProcessingGroup("amqpEvents")
public class OrderEventHandler {
    @QueryHandler
    public OrderDto on(OrderQuery query) {
        throw new UnsupportedOperationException();
    }
}
