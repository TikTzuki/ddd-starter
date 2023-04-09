package org.dyson.dddstarter.product.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;
import org.dyson.dddstarter.constant.Sequences;

import jakarta.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.PRODUCT_CATEGORY, sequenceName = Sequences.PRODUCT_CATEGORY, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.PRODUCT_CATEGORY)
    private Long id;
    private String name;

    @Transient
    private CategoryProductList products;

}
