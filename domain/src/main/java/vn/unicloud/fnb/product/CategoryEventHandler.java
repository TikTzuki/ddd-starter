package vn.unicloud.fnb.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.axonframework.common.ReflectionUtils;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.messaging.responsetypes.AbstractResponseType;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.unicloud.fnb.dto.CategoryDto;
import vn.unicloud.fnb.dto.CategoryQuery;

import java.beans.ConstructorProperties;
import java.lang.reflect.Type;
import java.util.concurrent.Future;


@Component
@ProcessingGroup("amqpEvents")
@RequiredArgsConstructor
public class CategoryEventHandler {
    private final CategoryRepository categoryRepository;

    @QueryHandler
    public Page<CategoryDto> on(CategoryQuery query) {
        return categoryRepository.findAll(query.getPageable())
                .map(category -> new CategoryDto(category.getId(), category.getName()));
    }
}
