package vn.unicloud.fnb.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.dyson.core.model.AggregateRoot;
import vn.unicloud.fnb.constant.Sequences;
import vn.unicloud.fnb.product.menu.MenuProductId;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Product extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.PRODUCT, sequenceName = Sequences.PRODUCT, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.PRODUCT)
    private Long id;

    private Long categoryId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "product")
    private List<ProductVariant> variants;

    @Transient
    private List<MenuProductId> menus;
}
