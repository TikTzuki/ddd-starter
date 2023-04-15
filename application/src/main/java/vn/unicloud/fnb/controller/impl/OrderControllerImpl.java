package vn.unicloud.fnb.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.dyson.core.dto.DataResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.fnb.controller.OrderController;
import vn.unicloud.fnb.dto.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderControllerImpl implements OrderController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Override
    public ResponseEntity<Object> get(Optional<Long> id, Pageable pageable) {
        OrderQuery query = new OrderQuery(id, pageable);
        var rs = queryGateway.query(query, OrderDto.class);
        log.info(rs.toString());
        return null;
    }

    @Override
    public ResponseEntity<Object> create(CreateOrderCommand command) {
        Object id = commandGateway.sendAndWait(command);
        log.debug("create order: {}", id);
        return new ResponseEntity<>(new DataResponse<>(id), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> update(UpdateOrderCommand command) {
        var id = commandGateway.sendAndWait(command);
        return new ResponseEntity<>(new DataResponse<>(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        commandGateway.sendAndWait(new DeleteOrderCommand(id));
        return ResponseEntity.noContent().build();
    }

}
