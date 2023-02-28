package org.tiktzuki.dddstater.domains.model.invoce;

import lombok.Getter;
import lombok.Setter;
import org.ddd.core.model.AggregateRoot;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "invoice")
@Entity
@Setter
@Getter
public class Invoice extends AggregateRoot<Long> {
    private String description;

    private Invoice(Long id) {
        super(id);
    }

    public Invoice() {
        this(0L); // FIXME
    }
}
