package org.dyson.dddstarter.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.dyson.dddstarter.invoice.model.Invoice;
import org.dyson.dddstarter.invoice.model.InvoiceRepository;
import org.dyson.dddstarter.order.model.OrderShipped;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceListener {
    private final InvoiceRepository invoiceRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderShipped(OrderShipped event) {
        log.info("--> invoice received event: {}", event);
        Invoice invoice = new Invoice();
        invoice.setDescription("--> invoice created");
        invoiceRepository.save(invoice);
        log.info("--> invoice saved {}", invoice.getId());
        throw new RuntimeException("zzz");
    }
}
