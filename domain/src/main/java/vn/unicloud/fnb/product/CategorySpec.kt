package vn.unicloud.fnb.product

import net.kaczmarzyk.spring.data.jpa.domain.Equal
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec
import org.springframework.data.jpa.domain.Specification
import vn.unicloud.fnb.product.model.Category

@Spec(path = "id", pathVars = ["id"], spec = Equal::class)
interface CategorySpec : Specification<Category?>
