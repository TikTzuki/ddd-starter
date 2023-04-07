package org.dyson.dddstater.domains.invoice.model;

import lombok.*;
import org.ddd.core.model.AggregateRoot;
import org.dyson.dddstater.constant.Sequences;

import jakarta.persistence.*;

@Table(name = "invoice")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Invoice extends AggregateRoot<Long> {
    @Id
    @SequenceGenerator(name = Sequences.INVOICE, sequenceName = Sequences.INVOICE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.INVOICE)
    private Long id;
    private String description;

}
