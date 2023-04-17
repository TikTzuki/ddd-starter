package vn.unicloud.fnb.domain.product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.LocalEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class ProductVariantItem extends LocalEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    ProductVariant variant;
}
