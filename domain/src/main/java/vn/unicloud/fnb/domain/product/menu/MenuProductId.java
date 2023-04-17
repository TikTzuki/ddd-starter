package vn.unicloud.fnb.domain.product.menu;

import jakarta.persistence.Embeddable;
import org.dyson.core.model.ValueObject;

import java.io.Serializable;

@Embeddable
public class MenuProductId implements ValueObject, Serializable {
    private Long menuId;
    private Long productId;

}
