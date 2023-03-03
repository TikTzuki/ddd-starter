package org.tiktzuki.dddstater.domains.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;
import org.tiktzuki.dddstater.domains.menu.model.MenuProductId;

import javax.persistence.*;
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
