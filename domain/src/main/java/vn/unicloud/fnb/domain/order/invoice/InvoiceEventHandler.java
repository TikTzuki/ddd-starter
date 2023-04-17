package vn.unicloud.fnb.domain.order.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.axonframework.modelling.command.LockAwareAggregate;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;
import vn.unicloud.fnb.domain.order.OrderShipped;
import vn.unicloud.fnb.dto.POSCharged;


@Slf4j
@Component
@RequiredArgsConstructor
@ProcessingGroup("amqpEvents")
public class InvoiceEventHandler {
    private final InvoiceRepository invoiceRepository;

    @EventHandler
    public void on(OrderShipped event) throws InterruptedException {
        log.info("--> received event1: {}", event);
        Invoice invoice = new Invoice();
        invoice.setDescription("--> invoice created");
//        invoiceRepository.save(invoice);
        log.info("--> invoice saved {}", invoice.getId());
    }


    @EventHandler
    public void on(POSCharged event) throws Exception {
        log.info("POS charged");
//        log.info(invoiceRepository.toString());
    }
}
