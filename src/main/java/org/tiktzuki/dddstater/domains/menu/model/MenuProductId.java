package org.tiktzuki.dddstater.domains.menu.model;

import org.ddd.core.model.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MenuProductId implements ValueObject, Serializable {
    private Long menuId;
    private Long productId;

}
