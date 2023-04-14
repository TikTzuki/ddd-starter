package vn.unicloud.fnb.product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

}
