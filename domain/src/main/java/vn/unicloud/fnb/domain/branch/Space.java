package vn.unicloud.fnb.domain.branch;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dyson.core.model.AggregateRoot;

@Data
@Entity
@Table(name = "space")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "space", comment = "Bàn/phòng trong chi nhánh")
public class Space extends AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

}
