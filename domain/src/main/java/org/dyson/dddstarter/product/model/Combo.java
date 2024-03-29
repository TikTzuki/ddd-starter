package org.dyson.dddstarter.product.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;
import org.dyson.dddstarter.constant.Sequences;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Table
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Combo extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.COMBO, sequenceName = Sequences.COMBO, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.COMBO)
    private Long id;

    private String name;

    @Column(columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<ComboItem> items;
}
