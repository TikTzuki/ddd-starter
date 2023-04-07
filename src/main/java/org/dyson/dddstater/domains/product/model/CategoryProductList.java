package org.dyson.dddstater.domains.product.model;

import lombok.Data;
import org.ddd.core.model.ValueObject;

import java.util.List;

@Data
public class CategoryProductList implements ValueObject {
    List<Product> products;
}
