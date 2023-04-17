package vn.unicloud.fnb.domain.product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.LocalEntity;

import java.util.List;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVariant extends LocalEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToOne
    private Product product;

    @OneToMany(mappedBy = "variant")
    private List<ProductVariantItem> variantItems;

}
