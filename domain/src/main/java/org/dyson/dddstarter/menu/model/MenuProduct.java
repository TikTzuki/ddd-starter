package org.dyson.dddstarter.menu.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.NodeEntity;

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
