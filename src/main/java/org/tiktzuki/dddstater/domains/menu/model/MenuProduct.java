package org.tiktzuki.dddstater.domains.menu.model;

import lombok.Data;
import org.ddd.core.model.NodeEntity;

import javax.persistence.*;

@Entity
@Table
@Data
public class MenuProduct extends NodeEntity<MenuProductId> {
    @EmbeddedId
    private MenuProductId id;

    @ManyToOne
    @MapsId("menuId")
    private Menu menu;

}
