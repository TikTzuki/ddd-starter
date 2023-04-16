package vn.unicloud.fnb.application.controller.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.ReflectionUtils;
import org.axonframework.messaging.responsetypes.AbstractResponseType;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.fnb.application.controller.CategoryController;
import vn.unicloud.fnb.dto.*;
import vn.unicloud.fnb.product.category.CategorySpec;

import java.beans.ConstructorProperties;
import java.lang.reflect.Type;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final ResponseType<Page<CategoryDto>> responseType = new PageResponseType<>(CategoryDto.class);

    @SneakyThrows
    @Override
    public ResponseEntity<Object> get(CategorySpec specification, Pageable pageable) {
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

class PageResponseType<R> extends AbstractResponseType<Page<R>> {


    /**
     * Instantiate a {@link ResponseType} with the given {@code expectedResponseType} as the type to be matched against and
     * to which the query response should be converted to, as is or as the contained type for an array/list/etc.
     *
     * @param expectedResponseType the response type which is expected to be matched against and to be returned, as is or as
     *                             the contained type for an array/list/etc
     */
    @JsonCreator
    @ConstructorProperties({"expectedResponseType"})
    public PageResponseType(@JsonProperty("expectedResponseType") Class<R> expectedResponseType) {
        super(expectedResponseType);
    }

    @Override
    public boolean matches(Type responseType) {
        Type unwrapped = ReflectionUtils.unwrapIfType(responseType, Future.class, Page.class);
        return isGenericAssignableFrom(unwrapped) || isAssignableFrom(unwrapped);
    }

    @Override
    public Page<R> convert(Object response) {
        return (Page<R>) response;
    }

    @Override
    public Class responseMessagePayloadType() {
        return Page.class;
    }

    @Override
    public String toString() {
        return "PageResponseType{" + expectedResponseType + "}";
    }

}
