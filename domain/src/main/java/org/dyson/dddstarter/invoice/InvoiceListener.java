package org.dyson.dddstarter.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.dyson.dddstarter.invoice.model.Invoice;
import org.dyson.dddstarter.invoice.model.InvoiceRepository;
import org.dyson.dddstarter.order.model.OrderShipped;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceListener {
    private final InvoiceRepository invoiceRepository;

    @EventHandler
    public void onOrderShipped(OrderShipped event) {
        log.info("--> invoice received event: {}", event);
        Invoice invoice = new Invoice();
        log.info("--> created invoice {}", invoice.getId());
        invoice.setDescription("--> invoice created");
        invoiceRepository.save(invoice);
        log.info("--> invoice saved");
    }
}
