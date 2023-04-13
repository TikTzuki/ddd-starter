package org.dyson.dddstarter.invoice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.dyson.dddstarter.invoice.model.Invoice;
import org.dyson.dddstarter.invoice.model.InvoiceRepository;
import org.dyson.dddstarter.order.OrderShipped;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceListener {
    private final InvoiceRepository invoiceRepository;

    @EventHandler
    public void on(OrderShipped event) throws InterruptedException {
        log.info("--> received event1: {}", event);
        Invoice invoice = new Invoice();
        invoice.setDescription("--> invoice created");
        invoiceRepository.save(invoice);
        log.info("--> invoice saved {}", invoice.getId());
    }

}
