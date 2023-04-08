package org.dyson.dddstarter.product.model;

import lombok.Data;
import org.dyson.core.model.ValueObject;

import java.util.List;

@Data
public class CategoryProductList implements ValueObject {
    List<Product> products;
}
