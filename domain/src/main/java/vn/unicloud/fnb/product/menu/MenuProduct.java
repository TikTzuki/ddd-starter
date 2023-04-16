package vn.unicloud.fnb.product.menu;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.LocalEntity;

@Entity
@Table
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuProduct extends LocalEntity<MenuProductId> {
    @EmbeddedId
    private MenuProductId id;

    @ManyToOne
    @MapsId("menuId")
    private Menu menu;

}
