package org.tiktzuki.dddstater.domains.branch.model;

import lombok.Data;
import org.ddd.core.model.AggregateRoot;
import org.tiktzuki.dddstater.constant.Sequences;

import javax.persistence.*;

@Data
@Entity
@Table(name = "space")
@org.hibernate.annotations.Table(appliesTo = "space", comment = "Bàn/phòng trong chi nhánh")
public class Space extends AggregateRoot<Long> {

    @Id
    @SequenceGenerator(name = Sequences.SPACE, sequenceName = Sequences.SPACE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.SPACE)
    private Long id;

}
