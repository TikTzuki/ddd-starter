package org.tiktzuki.dddstater.domains.product.model;

import lombok.Data;
import org.ddd.core.model.AggregateRoot;
import org.hibernate.annotations.Type;
import org.tiktzuki.dddstater.constant.Sequences;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
public class Combo extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.COMBO, sequenceName = Sequences.COMBO, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.COMBO)
    private Long id;

    private String name;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<ComboItem> items;
}
