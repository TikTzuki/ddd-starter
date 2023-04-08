package org.dyson.dddstarter.invoice.model;

import lombok.*;
import org.dyson.core.model.AggregateRoot;
import org.dyson.dddstarter.constant.Sequences;

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
