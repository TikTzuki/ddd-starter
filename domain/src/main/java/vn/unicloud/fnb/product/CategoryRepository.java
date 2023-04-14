package vn.unicloud.fnb.product;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.unicloud.fnb.product.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
