package org.dyson.dddstarter.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dyson.core.dto.DataResponse;
import org.dyson.dddstarter.CreateOrderCommand;
import org.dyson.dddstarter.UpdateOrderCommand;
import org.dyson.dddstarter.controller.OrderController;
import org.dyson.dddstarter.order.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    @Override
    public ResponseEntity<Object> getOrder() {
        return null;
    }

    @Override
    public ResponseEntity<Object> createOrder(CreateOrderCommand command) {
        orderService.createOder(command);
        return new ResponseEntity<>(new DataResponse<>(null), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateOrder(UpdateOrderCommand command) {
//        var id = commandGateway.sendAndWait(command);
        return new ResponseEntity<>(new DataResponse<>(null), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long id) {
//        commandGateway.sendAndWait(new DeleteOrderCommand(id));
        return ResponseEntity.noContent().build();
    }

}
