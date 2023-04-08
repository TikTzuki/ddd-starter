package org.dyson.dddstarter.product.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.NodeEntity;
import org.dyson.dddstarter.constant.Sequences;

import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class ProductVariantItem extends NodeEntity<Long> {
    @Id
    @SequenceGenerator(name = Sequences.PRODUCT_VARIANT_ITEM, sequenceName = Sequences.PRODUCT_VARIANT_ITEM, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.PRODUCT_VARIANT_ITEM)
    private Long id;

    @ManyToOne
    ProductVariant variant;
}
