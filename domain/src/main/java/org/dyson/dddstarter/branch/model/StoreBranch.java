package org.dyson.dddstarter.branch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;
import org.dyson.dddstarter.constant.Sequences;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class StoreBranch extends AggregateRoot<Long> {

    @Id
    @SequenceGenerator(name = Sequences.STORE_BRANCH, sequenceName = Sequences.STORE_BRANCH, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.STORE_BRANCH)
    private Long id;
}
