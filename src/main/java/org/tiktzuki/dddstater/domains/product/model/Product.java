package org.tiktzuki.dddstater.domains.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Product extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.PRODUCT, sequenceName = Sequences.PRODUCT, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.PRODUCT)
    private Long id;

    private String name;

    private String description;
}
