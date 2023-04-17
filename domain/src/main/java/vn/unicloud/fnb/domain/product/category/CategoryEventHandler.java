package vn.unicloud.fnb.domain.product.category;

import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.unicloud.fnb.dto.CategoryDto;
import vn.unicloud.fnb.dto.CategoryQuery;
import vn.unicloud.fnb.domain.product.model.Category;


@Component
@ProcessingGroup("amqpEvents")
@RequiredArgsConstructor
public class CategoryEventHandler {
    private final CategoryRepository categoryRepository;

    @QueryHandler
    public Page<CategoryDto> on(CategoryQuery<Category> query) {
        return categoryRepository.findAll(query.getSpecification(), query.getPageable())
            .map(category -> new CategoryDto(category.getId(), category.getName()));
    }
}