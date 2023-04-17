package vn.unicloud.fnb.application.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.fnb.application.controller.CategoryController;
import vn.unicloud.fnb.dto.*;
import vn.unicloud.fnb.domain.product.category.CategorySpec;
import vn.unicloud.fnb.infrastructure.PageResponseType;

import java.util.concurrent.ExecutionException;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final EventGateway eventGateway;
    private final ResponseType<Page<CategoryDto>> responseType = new PageResponseType<>(CategoryDto.class);

    @Override
    public ResponseEntity<Object> get(CategorySpec specification, Pageable pageable) throws ExecutionException, InterruptedException {
        Page<CategoryDto> categories = queryGateway.query(new CategoryQuery<>(specification, pageable), responseType).get();
        return ResponseEntity.ok(categories);
    }


    @Override
    public ResponseEntity<Object> create(CreateCategoryCommand command) {
        return ResponseEntity.ok(commandGateway.sendAndWait(command));
    }

    @Override
    public ResponseEntity<Object> update(UpdateCategoryCommand command) {
        return ResponseEntity.ok(commandGateway.sendAndWait(command));
    }

    @Override
    public ResponseEntity<Void> delete(DeleteCategoryCommand command) {
        commandGateway.sendAndWait(command);
        return ResponseEntity.noContent().build();
    }
}