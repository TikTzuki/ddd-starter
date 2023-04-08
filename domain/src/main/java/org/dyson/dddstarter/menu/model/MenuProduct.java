package org.dyson.dddstarter.menu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.NodeEntity;

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
