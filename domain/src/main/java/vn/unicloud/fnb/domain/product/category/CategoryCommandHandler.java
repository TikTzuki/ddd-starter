package vn.unicloud.fnb.domain.product.category;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;
import vn.unicloud.fnb.dto.CreateCategoryCommand;
import vn.unicloud.fnb.domain.product.model.Category;

@Component
public class CategoryCommandHandler {
    CategoryRepository categoryRepository;

    @CommandHandler
    Long handle(CreateCategoryCommand command) {
        Category category = new Category();
        category.setName(command.getName());
        categoryRepository.save(category);
        return category.getId();
    }
}
