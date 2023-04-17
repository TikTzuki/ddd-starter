package vn.unicloud.fnb.domain.branch;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.dyson.core.model.AggregateRoot;
import vn.unicloud.fnb.dto.CreateBranchCommand;

@Entity
@Table
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Aggregate
@org.axonframework.modelling.command.AggregateRoot
public class RestaurantBranch extends AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @AggregateIdentifier
    private Long id;

    @CommandHandler
    public RestaurantBranch(CreateBranchCommand command) {

    }
}
