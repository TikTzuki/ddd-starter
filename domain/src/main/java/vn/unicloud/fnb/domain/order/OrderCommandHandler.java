package vn.unicloud.fnb.domain.order;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import vn.unicloud.fnb.dto.DeleteOrderCommand;
import vn.unicloud.fnb.dto.UpdateOrderCommand;

@Component
public class OrderCommandHandler {

    @CommandHandler
    public void handle(UpdateOrderCommand command, OrderRepository orderRepository) {
        throw new UnsupportedOperationException();
    }

    @CommandHandler
    public void delete(DeleteOrderCommand command, OrderRepository orderRepository) {
        throw new UnsupportedOperationException();
    }
}
