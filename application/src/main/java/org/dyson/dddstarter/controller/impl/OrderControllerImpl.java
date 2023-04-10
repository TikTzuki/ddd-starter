package org.dyson.dddstarter.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.dyson.dddstarter.CreateOrderCommand;
import org.dyson.dddstarter.controller.OrderController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderControllerImpl implements OrderController {
    private final CommandGateway commandGateway;

    @Override
    public ResponseEntity<Object> createOrder(CreateOrderCommand createOrderCommand) {
        var id = commandGateway.sendAndWait(createOrderCommand);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

}
