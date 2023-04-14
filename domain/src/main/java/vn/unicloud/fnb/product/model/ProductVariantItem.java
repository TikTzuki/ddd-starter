package vn.unicloud.fnb.product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.LocalEntity;
import vn.unicloud.fnb.constant.Sequences;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class ProductVariantItem extends LocalEntity<Long> {
    @Id
    @SequenceGenerator(name = Sequences.PRODUCT_VARIANT_ITEM, sequenceName = Sequences.PRODUCT_VARIANT_ITEM, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.PRODUCT_VARIANT_ITEM)
    private Long id;

    @ManyToOne
    ProductVariant variant;
}
