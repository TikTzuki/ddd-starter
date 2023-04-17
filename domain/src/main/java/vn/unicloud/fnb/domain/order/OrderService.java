package vn.unicloud.fnb.domain.order;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.stereotype.Service;
import vn.unicloud.fnb.dto.CreateOrderCommand;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final EventGateway eventGateway;

    //    @CommandHandler
    public Long createOder(CreateOrderCommand command) {
        Order order = orderFactory.createOrder();
        order.ship();
        orderRepository.save(order);
        return order.getId();
    }
}
