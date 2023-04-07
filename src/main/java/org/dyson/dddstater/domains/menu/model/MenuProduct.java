package org.dyson.dddstater.domains.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ddd.core.model.NodeEntity;

import jakarta.persistence.*;

@Entity
@Table
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuProduct extends NodeEntity<MenuProductId> {
    @EmbeddedId
    private MenuProductId id;

    @ManyToOne
    @MapsId("menuId")
    private Menu menu;

}
