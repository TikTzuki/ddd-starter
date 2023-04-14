package vn.unicloud.fnb.order;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Service;
import vn.unicloud.fnb.dto.CreateOrderCommand;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;

    @CommandHandler
    public void createOder(CreateOrderCommand command) {
        Order order = orderFactory.createOrder();
        order.ship();
        orderRepository.save(order);
    }
}
