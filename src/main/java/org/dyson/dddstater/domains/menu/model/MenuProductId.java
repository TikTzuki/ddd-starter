package org.dyson.dddstater.domains.menu.model;

import jakarta.persistence.Embeddable;
import org.ddd.core.model.ValueObject;

import java.io.Serializable;

@Embeddable
public class MenuProductId implements ValueObject, Serializable {
    private Long menuId;
    private Long productId;

}
