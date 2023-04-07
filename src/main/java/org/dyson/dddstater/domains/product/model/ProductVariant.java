package org.dyson.dddstater.domains.product.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ddd.core.model.NodeEntity;
import org.dyson.dddstater.constant.Sequences;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
@Data
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
