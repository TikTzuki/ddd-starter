package vn.unicloud.fnb.domain.order.invoice.model;

import jakarta.persistence.*;
import lombok.*;
import org.dyson.core.model.AggregateRoot;

@Table
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Invoice extends AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;

}
