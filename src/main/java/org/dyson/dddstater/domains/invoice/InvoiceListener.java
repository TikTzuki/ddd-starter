package org.dyson.dddstater.domains.invoice;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.dyson.dddstater.domains.invoice.model.Invoice;
import org.dyson.dddstater.domains.invoice.model.InvoiceRepository;
import org.dyson.dddstater.domains.order.model.OrderShipped;


@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceListener {
    private final InvoiceRepository invoiceRepository;

    @EventListener
    @Transactional
    public void onOrderShipped(OrderShipped event) {
        log.info("--> invoice received event: {}", event);
        Invoice invoice = new Invoice();
        log.info("--> created invoice {}", invoice.getId());
        invoice.setDescription("--> invoice created");
        invoiceRepository.save(invoice);
        log.info("--> invoice saved");
    }
}