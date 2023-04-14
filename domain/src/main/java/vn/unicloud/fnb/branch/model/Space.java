package vn.unicloud.fnb.branch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;
import vn.unicloud.fnb.constant.Sequences;

@Data
@Entity
@Table(name = "space")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "space", comment = "Bàn/phòng trong chi nhánh")
public class Space extends AggregateRoot<Long> {

    @Id
    @SequenceGenerator(name = Sequences.SPACE, sequenceName = Sequences.SPACE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Sequences.SPACE)
    private Long id;

}
