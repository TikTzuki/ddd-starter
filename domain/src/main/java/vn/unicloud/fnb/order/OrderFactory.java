package vn.unicloud.fnb.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFactory {
    private final OrderRepository orderRepository;

    public Order createOrder() {
        return orderRepository.save(new Order());
    }

}
