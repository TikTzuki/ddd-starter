package org.tiktzuki.dddstater.domains.branch.model;

import lombok.Data;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;

import javax.persistence.*;

@Entity
@Table
@Data
public class StoreBranch extends AggregateRoot<Long> {

    @Id
    @SequenceGenerator(name = Sequences.STORE_BRANCH, sequenceName = Sequences.STORE_BRANCH, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.STORE_BRANCH)
    private Long id;
}
