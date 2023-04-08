package org.dyson.dddstarter.product.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.NodeEntity;
import org.dyson.dddstarter.constant.Sequences;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVariant extends NodeEntity<Long> {

    @Id
    @SequenceGenerator(name = Sequences.PRODUCT_VARIANT, sequenceName = Sequences.PRODUCT_VARIANT, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.PRODUCT_VARIANT)
    private Long id;

    private String name;

    @ManyToOne
    private Product product;

    @OneToMany(mappedBy = "variant")
    private List<ProductVariantItem> variantItems;

}
