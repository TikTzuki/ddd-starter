package org.tiktzuki.dddstater.domains.product.model;

import lombok.Data;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;

import javax.persistence.*;

@Entity
@Table
@Data
public class Category extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.PRODUCT_CATEGORY, sequenceName = Sequences.PRODUCT_CATEGORY, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.PRODUCT_CATEGORY)
    private Long id;
    private String name;

    @Transient
    private CategoryProductList products;

}
