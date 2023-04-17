package vn.unicloud.fnb.domain.order.invoice;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.dyson.core.model.AggregateRoot;
import vn.unicloud.fnb.dto.POSCharged;

@AllArgsConstructor
@RequiredArgsConstructor
@Aggregate
public class Invoice {

    @AggregateIdentifier
    private Long id;
    private String description;


}
