package org.tiktzuki.dddstater.domains.invoice.model;

import lombok.*;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;

import javax.persistence.*;

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
