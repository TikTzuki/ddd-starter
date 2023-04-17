package vn.unicloud.fnb.domain.diner;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.dyson.core.model.AggregateRoot;

@Aggregate
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Diner extends AggregateRoot<Long> {
    @Id
    @AggregateIdentifier
    Long id;

}
